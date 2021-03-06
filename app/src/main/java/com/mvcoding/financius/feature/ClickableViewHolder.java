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

package com.mvcoding.financius.feature;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;

import lombok.Getter;
import rx.subjects.PublishSubject;

public class ClickableViewHolder extends BaseViewHolder {
    public ClickableViewHolder(@NonNull View itemView, @NonNull PublishSubject<ViewHolderClickEvent> clickSubject) {
        super(itemView);
        itemView.setOnClickListener(v -> clickSubject.onNext(new ViewHolderClickEvent(v, getAdapterPosition())));
    }

    @Getter public static class ViewHolderClickEvent {
        private final View view;
        private final int position;

        public ViewHolderClickEvent(@NonNull View view, @IntRange(from = 0) int position) {
            this.view = view;
            this.position = position;
        }
    }
}
