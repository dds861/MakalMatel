package com.dd.database.sqlite.ui

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseViewModel
import com.dd.database.sqlite.ui.home.EmaHomeNavigator

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Created by: Carlos Mateo Benito on 20/1/19.
 */
class EmaHomeToolbarViewModel : BaseViewModel<EmaHomeToolbarState, EmaHomeNavigator.Navigation>() {

    override fun onStartFirstTime(statePreloaded: Boolean) {

    }

    override fun onResultListenerSetup() {
       addOnResultReceived{
           (it.data as? Pair<*, *>)?.also { pair ->
               notifySingleEvent(EmaExtraData(extraData = pair))
           }
       }
    }

    fun setToolbarTitle(title:String?){
        updateToNormalState {
            copy(toolbarTitle = title)
        }
    }

    override val initialViewState: EmaHomeToolbarState = EmaHomeToolbarState()
}