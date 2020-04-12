package com.dd.database.sqlite.ui

import android.widget.Toast
import com.carmabs.ema.android.extension.DATE_FORMAT_HHMM
import com.carmabs.ema.android.extension.getFormattedString
import com.carmabs.ema.android.extension.toDateFormat
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseActivity
import com.dd.database.sqlite.R
import com.dd.database.sqlite.ui.home.EmaHomeNavigator
import org.kodein.di.generic.instance

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Created by: Carlos Mateo Benito on 20/1/19.
 */
class EmaHomeActivity : BaseActivity<EmaHomeToolbarState, EmaHomeToolbarViewModel, EmaHomeNavigator.Navigation>() {

    override val navGraph: Int = com.dd.database.sqlite.R.navigation.navigation_ema_home

    override fun onInitialized(viewModel: EmaHomeToolbarViewModel) {

    }

    override fun provideFixedToolbarTitle(): String? = getString(R.string.home_toolbar_title)

    /**
     * Variable used to enable the theme used in manifest. Otherwise it will use the EmaTheme,
     * It is set as true by default.
     */
    override val overrideTheme: Boolean = false

    override val viewModelSeed: EmaHomeToolbarViewModel by instance()

    override val navigator: EmaHomeNavigator by instance()

    override fun onStateNormal(data: EmaHomeToolbarState) {
        setToolbarTitle(data.toolbarTitle)

    }

    override fun onStateAlternative(data: EmaExtraData) {

    }



    override fun onSingleEvent(data: EmaExtraData) {
        when(data.type){
             EmaExtraData.DEFAULT_ID -> {
                 (data.extraData as? Pair<*, *>)?.also {
                     (it.second as? Long)?.also { timestamp ->
                         try {
                             val date = timestamp.toDateFormat(DATE_FORMAT_HHMM)
                             Toast.makeText(this,
                                     R.string.home_last_user.getFormattedString(this,it.first,date),
                                     Toast.LENGTH_SHORT).show()
                             return
                         } catch (e: Exception) { }
                     }

                 }
             }
        }
    }

    override fun onStateError(error: Throwable) {

    }
}