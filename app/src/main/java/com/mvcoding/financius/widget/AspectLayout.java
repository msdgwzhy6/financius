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

package com.mvcoding.financius.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.mvcoding.financius.R;

public class AspectLayout extends FrameLayout {
    private AspectBase aspectBase;
    private float aspect;

    public AspectLayout(Context context) {
        super(context);
        init(context, null);
    }

    public AspectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AspectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public AspectBase getAspectBase() {
        return aspectBase;
    }

    public void setAspectBase(AspectBase aspectBase) {
        this.aspectBase = aspectBase;
        requestLayout();
    }

    public float getAspect() {
        return aspect;
    }

    public void setAspect(float aspect) {
        this.aspect = aspect;
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width;
        final int height;
        switch (aspectBase) {
            case Width:
                width = getMeasuredWidth();
                height = (int) (width * aspect);
                break;
            case Height:
                height = getMeasuredHeight();
                width = (int) (height * aspect);
                break;
            case Smallest: {
                final int measuredWidth = getMeasuredWidth();
                final int measuredHeight = getMeasuredHeight();
                if (measuredWidth < measuredHeight) {
                    width = measuredWidth;
                    height = (int) (width * aspect);
                } else if (measuredWidth > measuredHeight) {
                    height = measuredHeight;
                    width = (int) (height * aspect);
                } else {
                    width = measuredWidth;
                    height = measuredHeight;
                }
                break;
            }
            case Biggest: {
                final int measuredWidth = getMeasuredWidth();
                final int measuredHeight = getMeasuredHeight();
                if (measuredWidth < measuredHeight) {
                    height = measuredHeight;
                    width = (int) (height * aspect);
                } else if (measuredWidth > measuredHeight) {
                    width = measuredWidth;
                    height = (int) (width * aspect);
                } else {
                    width = measuredWidth;
                    height = measuredHeight;
                }
                break;
            }
            default:
                throw new IllegalArgumentException("SquareBasedOn " + aspectBase + " is not supported.");
        }

        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    private void init(Context context, AttributeSet attrs) {
        final AspectBase aspectBase;
        final float aspect;

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectLayout);

            aspectBase = AspectBase.values()[a.getInt(R.styleable.AspectLayout_aspectBase, AspectBase.Smallest.ordinal())];
            aspect = a.getFloat(R.styleable.AspectLayout_aspect, 1);

            a.recycle();
        } else {
            aspectBase = AspectBase.Smallest;
            aspect = 1;
        }

        setAspectBase(aspectBase);
        setAspect(aspect);
    }

    public enum AspectBase {
        Width, Height, Smallest, Biggest
    }
}
