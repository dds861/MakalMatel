package com.dd.database.sqlite.injection

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.android.ui.EmaFragmentActivity
import com.dd.database.sqlite.ui.EmaHomeToolbarViewModel
import com.dd.database.sqlite.ui.category.CategoryNavigator
import com.dd.database.sqlite.ui.makal.MakalNavigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


fun activityInjection(activity: Activity) = Kodein.Module(name = "ActivityModule") {

    bind<Activity>() with singleton { activity }

    bind<NavController>() with singleton { (activity as EmaFragmentActivity).let { it.navController } }


    bind<EmaHomeToolbarViewModel>() with singleton { EmaHomeToolbarViewModel() }

    bind<CategoryNavigator>() with singleton { CategoryNavigator(instance(), instance()) }

    bind<MakalNavigator>() with singleton { MakalNavigator(instance(), instance()) }

}