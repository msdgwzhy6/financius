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

package com.mvcoding.financius.core.splash;

import com.mvcoding.financius.core.Navigator;
import com.mvcoding.financius.core.Session;
import com.mvcoding.financius.core.UserSettings;

import org.junit.Test;

import static com.mvcoding.financius.core.SessionForTest.aSession;
import static com.mvcoding.financius.core.UserSettingsForTest.aUserSettings;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SplashViewModelTest {
    @Test public void showsOverviewWhenUserIsLoggedIn() throws Exception {
        Session session = aSession().thatIsLoggedIn();
        UserSettings userSettings = aUserSettings();
        Navigator navigator = mock(Navigator.class);

        new SplashViewModel(session, userSettings, navigator);

        verify(navigator).showOverview();
    }

    @Test public void showsOverviewWhenUserHasSeenIntroduction() throws Exception {
        Session session = aSession();
        UserSettings userSettings = aUserSettings().withIntroductionSeen();
        Navigator navigator = mock(Navigator.class);

        new SplashViewModel(session, userSettings, navigator);

        verify(navigator).showOverview();
    }

    @Test public void showsIntroductionWhenUserIsNotLoggedInAndHasNotSeenIntroduction() throws Exception {
        Session session = aSession().thatIsNotLoggedIn();
        UserSettings userSettings = aUserSettings().withIntroductionNotSeen();
        Navigator navigator = mock(Navigator.class);

        new SplashViewModel(session, userSettings, navigator);

        verify(navigator).showIntroduction();
    }
}