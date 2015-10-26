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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mvcoding.financius.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TagItemView extends LinearLayout {
    @Bind(R.id.checkBox) CheckBox checkBox;
    @Bind(R.id.colorImageView) ImageView colorImageView;
    @Bind(R.id.titleTextView) TextView titeTextView;

    public TagItemView(Context context) {
        super(context);
    }

    public TagItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }
}
