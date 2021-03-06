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

package com.mvcoding.financius.util.date;

import android.content.Context;
import android.support.annotation.NonNull;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;

public final class DateFormatter {
    public static String date(@NonNull Context context, long date) {
        return DateUtils.formatDateTime(context, new DateTime(date), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY);
    }

    public static String time(@NonNull Context context, long date) {
        return DateUtils.formatDateTime(context, new DateTime(date), DateUtils.FORMAT_SHOW_TIME);
    }
}
