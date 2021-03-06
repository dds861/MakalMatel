package com.dd.database.sqlite.injection

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.carmabs.ema.android.ui.dialog.EmaBaseDialogProvider
import com.dd.database.sqlite.DIALOG_TAG_LOADING
import com.dd.database.sqlite.DIALOG_TAG_SIMPLE
import com.dd.database.sqlite.dialog.loading.LoadingDialogProvider
import com.dd.database.sqlite.dialog.simple.SimpleDialogProvider
import com.dd.database.sqlite.ui.category.CategoryViewModel
import com.dd.database.sqlite.ui.makal.MakalViewModel
import com.dd.database.sqlite.ui.search.SearchViewModel
import com.dd.database.sqlite.ui.widget.WidgetViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun fragmentInjection(fragment: Fragment) = Kodein.Module(name = "FragmentModule") {
    bind<Fragment>() with singleton { fragment }

    bind<FragmentManager>() with singleton { fragment.requireActivity().supportFragmentManager }

    bind<EmaBaseDialogProvider>(tag = DIALOG_TAG_SIMPLE) with provider { SimpleDialogProvider(instance()) }

    bind<EmaBaseDialogProvider>(tag = DIALOG_TAG_LOADING) with provider { LoadingDialogProvider(instance()) }

    bind<CategoryViewModel>() with provider { CategoryViewModel(instance(), instance()) }

    bind<MakalViewModel>() with provider { MakalViewModel(instance(), instance()) }

    bind<SearchViewModel>() with provider { SearchViewModel(instance(), instance(), instance()) }

    bind<WidgetViewModel>() with provider { WidgetViewModel(instance()) }
}