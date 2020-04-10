package com.dd.database.sqlite.ema.ui.backdata;

import androidx.core.content.ContextCompat
import com.carmabs.ema.android.ui.EmaActivity
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.ema.base.BaseActivity
import com.dd.database.sqlite.R
import org.kodein.di.generic.instance

/**
 *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 * <p>
 * Date: 2019-11-07
 */

class EmaBackToolbarActivity : BaseActivity<EmaBackToolbarState, EmaBackToolbarViewModel, EmaBackNavigator.Navigation>() {

    override val navGraph: Int = com.dd.database.sqlite.R.navigation.navigation_ema_back

    override fun provideFixedToolbarTitle(): String? = null

    override val viewModelSeed: EmaBackToolbarViewModel by instance()

    override val navigator: EmaBackNavigator by instance()

    override fun onInitialized(viewModel: EmaBackToolbarViewModel) {
        toolbar.apply {
            setBackgroundColor(ContextCompat.getColor(this@EmaBackToolbarActivity, R.color.colorPrimary))
        }

    }

    override fun onStateNormal(data: EmaBackToolbarState) {

    }

    override fun onStateAlternative(data: EmaExtraData) {

    }

    override fun onSingleEvent(data: EmaExtraData) {

    }

    override fun onStateError(error: Throwable) {

    }
}