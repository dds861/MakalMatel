package com.dd.database.sqlite.ui.makals

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseFragment
import org.kodein.di.generic.instance
import com.dd.database.sqlite.R


class MakalViewFragment
    : BaseFragment<MakalState, MakalViewModel, MakalNavigator.Navigation>() {

    /**
     * Default variables
     */

    override val layoutId: Int = R.layout.fragment_makal

    override val navigator: MakalNavigator by instance()

    override val viewModelSeed: MakalViewModel by instance()

    /**
     * Default functions
     */

    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onNormal(data: MakalState) {
    }

    override fun onError(error: Throwable) {}


    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onSingle(data: EmaExtraData) {
    }

    override fun onInitialized(viewModel: MakalViewModel) {
    }

}
