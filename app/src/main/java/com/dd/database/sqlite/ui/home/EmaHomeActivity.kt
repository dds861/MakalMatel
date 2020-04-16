package com.dd.database.sqlite.ui.home

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseActivity
import org.kodein.di.generic.instance

class EmaHomeActivity : BaseActivity<EmaHomeToolbarState, EmaHomeToolbarViewModel, EmaHomeNavigator.Navigation>() {
    override val navGraph: Int = com.dd.database.sqlite.R.navigation.navigation_ema_home

    override fun onInitialized(viewModel: EmaHomeToolbarViewModel) {
    }

    override fun provideFixedToolbarTitle(): String? = getString(R.string.home_toolbar_title)

    /**
     * Variable used to enable the theme used in manifest. Otherwise it will use the EmaTheme,
     * It is set as true by default.
     */
    override val overrideTheme: Boolean = true
    override val viewModelSeed: EmaHomeToolbarViewModel by instance()
    override val navigator: EmaHomeNavigator by instance()


    override fun onStateNormal(data: EmaHomeToolbarState) {
        setToolbarTitle(data.toolbarTitle)

    }

    override fun onStateAlternative(data: EmaExtraData) {
    }

    override fun onSingleEvent(data: EmaExtraData) {

    }

    override fun onStateError(error: Throwable) {
    }
}