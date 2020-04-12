package com.dd.database.sqlite.ui.backdata

import androidx.navigation.NavController
import com.carmabs.ema.android.navigation.EmaNavigator
import com.carmabs.ema.core.navigator.EmaBaseNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.database.sqlite.R

/**
 *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Date: 2019-11-07
 */

class EmaBackNavigator(
        override val navController: NavController
) : EmaNavigator<EmaBackNavigator.Navigation> {

    sealed class Navigation : EmaNavigationState {

        object Result : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                (navigator as? EmaBackNavigator)?.toResult()
            }
        }
    }

    private fun toResult() {
        navigateWithAction(R.id.action_emaBackFragment_to_emaBackResultFragment)
    }
}