package com.dd.database.sqlite.ui.home

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseViewModel
import com.dd.database.sqlite.model.ToolbarModel

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


    fun onActionUpdateToolbar(update: Boolean = true, updateToolbar: (ToolbarModel) -> ToolbarModel) {
        checkDataState{
            if(update)
                updateToNormalState {
                    copy(toolbarModel = updateToolbar.invoke(it.toolbarModel))
                }
            else
                updateDataState {
                    copy(toolbarModel = updateToolbar.invoke(it.toolbarModel))
                }
        }
    }
}