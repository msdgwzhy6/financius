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

package com.mvcoding.financius.feature.splash;

import android.support.annotation.NonNull;

import com.mvcoding.financius.UserSettings;
import com.mvcoding.financius.api.Session;
import com.mvcoding.financius.feature.ActivityScope;
import com.mvcoding.financius.feature.Presenter;
import com.mvcoding.financius.feature.PresenterView;

import javax.inject.Inject;

@ActivityScope class SplashPresenterOld extends Presenter<SplashPresenterOld.View> {
    private final Session session;
    private final UserSettings userSettings;

    @Inject SplashPresenterOld(@NonNull Session session, @NonNull UserSettings userSettings) {
        this.session = session;
        this.userSettings = userSettings;
    }

    @Override protected void onViewAttached(@NonNull View view) {
        super.onViewAttached(view);

        if (session.isLoggedIn() || userSettings.isIntroductionSeen()) {
            view.startOverviewAndClose();
        } else {
            view.startIntroductionAndClose();
        }
    }

    public interface View extends PresenterView {
        void startOverviewAndClose();
        void startIntroductionAndClose();
    }
}
