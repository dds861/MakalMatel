package com.dd.database.sqlite.ema.ui.user

import com.carmabs.ema.core.navigator.EmaNavigationState
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.ema.base.BaseViewModel
import com.dd.database.sqlite.ema.ui.home.EmaHomeToolbarViewModel
import com.dd.domain.manager.ResourceManager

class EmaUserViewModel(private val resourceManager: ResourceManager) : BaseViewModel<EmaUserState, EmaNavigationState>() {

    var toolbarViewModel: EmaHomeToolbarViewModel? = null

    companion object {
        const val SINGLE_EVENT_GROUP = 0
        const val SINGLE_EVENT_USER = 1
    }

    override fun onStartFirstTime(statePreloaded: Boolean) {
        val list = createListItems()
        updateToNormalState {
            copy(itemList = list)
        }
    }

    private fun createListItems(): List<EmaUserItemModel> {
        return listOf(
                EmaUserRightModel(3),
                EmaUserLeftModel("Carmabs"),
                EmaUserLeftModel("EMA"),
                EmaUserRightModel(6)
        )
    }


    override val initialViewState: EmaUserState = EmaUserState()


    fun onActionUserClicked(item: EmaUserItemModel) {
        val eventID = when (item.type) {
            EmaUserItemModel.Type.LEFT -> {
                toolbarViewModel?.setToolbarTitle((item as EmaUserLeftModel).name)
                SINGLE_EVENT_USER
            }
            EmaUserItemModel.Type.RIGHT -> {
                toolbarViewModel?.setToolbarTitle(resourceManager.getNumberPeople((item as EmaUserRightModel).number))
                SINGLE_EVENT_GROUP
            }
        }

        notifySingleEvent(EmaExtraData(eventID, item))
    }

    override fun onCleared() {
        //To restore the default title, if not next time activity goes to foreground, this title
        //will be set in the toolbar
        toolbarViewModel?.setToolbarTitle(null)
        super.onCleared()
    }
}
