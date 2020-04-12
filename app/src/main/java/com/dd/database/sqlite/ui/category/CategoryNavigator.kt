package com.dd.database.sqlite.ui.category

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.database.sqlite.base.BaseNavigator

class CategoryNavigator(
        override val navController: NavController,
        private val activity: Activity)
    : BaseNavigator<CategoryNavigator.Navigation>() {

    sealed class Navigation : EmaNavigationState {
    }
}