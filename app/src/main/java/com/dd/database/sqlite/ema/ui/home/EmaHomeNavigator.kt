package com.dd.database.sqlite.ema.ui.home

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavController
import com.carmabs.ema.android.navigation.EmaNavigator
import com.carmabs.ema.core.navigator.EmaBaseNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.database.sqlite.ema.ui.error.EmaErrorToolbarViewActivity
import com.dd.database.sqlite.R
import com.dd.database.sqlite.ema.ui.user.EmaUserState

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Created by: Carlos Mateo Benito on 20/1/19.
 */
class EmaHomeNavigator(override val navController: NavController, val activity: Activity) : EmaNavigator<EmaHomeNavigator.Navigation> {

    sealed class Navigation : EmaNavigationState {

        class User(private val emaUserState: EmaUserState) : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                (navigator as? EmaHomeNavigator)?.toUser(emaUserState)
            }
        }

        object Self : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                (navigator as? EmaHomeNavigator)?.toSelf()
            }
        }


        object Error: Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                (navigator as? EmaHomeNavigator)?.toError()
            }
        }
    }

    private fun toSelf() {
        navigateWithAction(
                R.id.action_homeViewFragment_self)
    }

    private fun toUser(emaUserState: EmaUserState) {
        navigateWithAction(
                R.id.action_homeViewFragment_to_userFragment,
                addInputState(emaUserState))
    }

    private fun toError() {
        //Use this method to enable back data to activity
        navigateToEmaActivityWithResult(activity, Intent(activity, EmaErrorToolbarViewActivity::class.java))
    }
}