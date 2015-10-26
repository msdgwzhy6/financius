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

package com.mvcoding.financius.ui.tag;

import android.support.annotation.NonNull;

import com.mvcoding.financius.data.model.Tag;
import com.mvcoding.financius.ui.Presenter;
import com.mvcoding.financius.ui.PresenterView;

import rx.Observable;

public class TagItemPresenter extends Presenter<TagItemPresenter.View> {
    private final Tag tag;

    public TagItemPresenter(@NonNull Tag tag) {
        this.tag = tag;
    }

    @Override protected void onViewAttached(@NonNull View view) {
        super.onViewAttached(view);
        view.showTag(tag);
    }

    public interface View extends PresenterView {
        Observable<Tag> onTagChanged();
        void showTag(@NonNull Tag tag);
    }
}
