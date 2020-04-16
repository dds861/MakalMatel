package com.dd.database.sqlite.ui.home

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseViewModel

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