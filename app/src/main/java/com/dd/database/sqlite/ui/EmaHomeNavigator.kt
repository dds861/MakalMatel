package com.dd.database.sqlite.ui

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.android.navigation.EmaNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState


class EmaHomeNavigator(override val navController: NavController, val activity: Activity) : EmaNavigator<EmaHomeNavigator.Navigation> {

    sealed class Navigation : EmaNavigationState {
    }
}