/*
 * Copyright (C) 2015 Mantas Varnagiris.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.mvcoding.financius.feature.tag;

import android.support.annotation.NonNull;

import com.mvcoding.financius.data.DataSaveApi;
import com.mvcoding.financius.data.converter.TagConverter;
import com.mvcoding.financius.data.model.Tag;
import com.mvcoding.financius.feature.ActivityScope;
import com.mvcoding.financius.feature.CloseablePresenterView;
import com.mvcoding.financius.feature.ErrorPresenterView;
import com.mvcoding.financius.feature.Presenter;
import com.mvcoding.financius.feature.PresenterView;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

@ActivityScope class TagPresenter extends Presenter<TagPresenter.View> {
    private final Tag tag;
    private final DataSaveApi dataSaveApi;
    private final TagConverter tagConverter;
    private final Scheduler uiScheduler;
    private final Scheduler ioScheduler;

    TagPresenter(@NonNull Tag tag, @NonNull DataSaveApi dataSaveApi, @NonNull TagConverter tagConverter, @NonNull @Named("ui") Scheduler uiScheduler, @NonNull @Named("io") Scheduler ioScheduler) {
        this.tag = tag;
        this.dataSaveApi = dataSaveApi;
        this.tagConverter = tagConverter;
        this.uiScheduler = uiScheduler;
        this.ioScheduler = ioScheduler;
    }

    @Override protected void onViewAttached(@NonNull View view) {
        super.onViewAttached(view);

        unsubscribeOnDetach(view.onSave()
                                    .withLatestFrom(tagObservable(view).doOnNext(view::showTag), (event, tag) -> tag)
                                    .filter(this::validate)
                                    .observeOn(ioScheduler)
                                    .flatMap(dataSaveApi::saveTag)
                                    .observeOn(uiScheduler)
                                    .subscribeOn(uiScheduler)
                                    .subscribe(view::startResult, throwable -> showFatalError(throwable, view, view, uiScheduler)));
    }

    @NonNull private Observable<Tag> tagObservable(@NonNull View view) {
        final Observable<Tag> tagObservable = Observable.just(tag);
        final Observable<String> titleObservable = view.onTitleChanged().startWith(tag.getTitle());
        final Observable<Integer> colorObservable = view.onColorChanged().startWith(tag.getColor());
        return Observable.combineLatest(tagObservable, titleObservable, colorObservable, (tag, title, color) -> {
            tag.setTitle(title);
            tag.setColor(color);
            return tag;
        });
    }

    private boolean validate(@NonNull Tag tag) {
        try {
            tagConverter.toBody(tag).validate();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public interface View extends PresenterView, ErrorPresenterView, CloseablePresenterView {
        @NonNull Observable<String> onTitleChanged();
        @NonNull Observable<Integer> onColorChanged();
        @NonNull Observable<Object> onSave();
        void showTag(@NonNull Tag tag);
        void startResult(@NonNull Tag tag);
    }
}
