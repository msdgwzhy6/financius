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

package com.mvcoding.financius.app.splash;

import android.os.Bundle;

import com.mvcoding.financius.core.splash.SplashViewModel;
import com.mvcoding.financius.feature.BaseActivity2;

import static com.mvcoding.financius.app.BackendSession.session;
import static com.mvcoding.financius.app.PersistedUserSettings.userSettings;
import static com.mvcoding.financius.app.ViewNavigator.navigator;

public class SplashActivity extends BaseActivity2 {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SplashViewModel(session(), userSettings(), navigator());
    }
}
