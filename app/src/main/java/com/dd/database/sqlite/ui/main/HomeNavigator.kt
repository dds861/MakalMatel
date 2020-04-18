package com.dd.database.sqlite.ui.main

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.navigation.NavController
import com.carmabs.ema.android.navigation.EmaNavigator
import com.carmabs.ema.core.navigator.EmaBaseNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.database.sqlite.R

class HomeNavigator(override val navController: NavController, val activity: Activity) : EmaNavigator<HomeNavigator.Navigation> {
    sealed class Navigation : EmaNavigationState {
        object Back : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                val nav = navigator as HomeNavigator
                nav.toBack()
            }
        }

        object Telegram : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                val nav = navigator as HomeNavigator
                nav.toTelegram()
            }
        }
    }

    private fun toTelegram() {
        activity.applicationContext.startActivity(try {
            activity.applicationContext.packageManager.getPackageInfo(activity.applicationContext.resources.getString(R.string.telegramPackage), PackageManager.GET_ACTIVITIES)
            Intent(Intent.ACTION_VIEW, Uri.parse(activity.applicationContext.resources.getString(R.string.telegramDirectLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        } catch (e: PackageManager.NameNotFoundException) {
            Intent(Intent.ACTION_VIEW, Uri.parse(activity.applicationContext.resources.getString(R.string.telegramWebLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    private fun toBack() {
        if (!navigateBack())
            activity.finish()
    }
}