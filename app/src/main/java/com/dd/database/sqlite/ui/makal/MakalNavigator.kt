package com.dd.database.sqlite.ui.makal

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.core.navigator.EmaBaseNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.database.sqlite.base.BaseNavigator
import com.dd.database.sqlite.ui.category.CategoryNavigator

class MakalNavigator(
        override val navController: NavController,
        private val activity: Activity)
    : BaseNavigator<MakalNavigator.Navigation>() {
    sealed class Navigation : EmaNavigationState {
        class Search(private val makalState: MakalState) : MakalNavigator.Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                val nav = navigator as CategoryNavigator
            }
        }
    }
}