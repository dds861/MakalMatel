package com.dd.database.sqlite.ui.makal

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.database.sqlite.base.BaseNavigator

class MakalNavigator(
        override val navController: NavController,
        private val activity: Activity)
    : BaseNavigator<MakalNavigator.Navigation>() {

    sealed class Navigation : EmaNavigationState {
    }
}