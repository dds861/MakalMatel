package com.dd.database.sqlite.ui.main

import android.util.Log
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseViewModel
import com.dd.database.sqlite.model.ToolbarModel

class MainToolbarsViewModel : BaseViewModel<HomeToolbarState, HomeNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val initialViewState: HomeToolbarState = HomeToolbarState()

    /**
     * Default functions
     */
    override fun onStartFirstTime(statePreloaded: Boolean) {
    }

    override fun onResultListenerSetup() {
        addOnResultReceived {
            (it.data as? Pair<*, *>)?.also { pair ->
                notifySingleEvent(EmaExtraData(extraData = pair))
            }
        }
    }

    /**
     * Custom functions
     */
    fun onActionUpdateToolbar(update: Boolean = true, updateToolbar: (ToolbarModel) -> ToolbarModel) {
        checkDataState {
            if (update)
                updateToNormalState {
                    copy(
                            toolbarModel = updateToolbar.invoke(it.toolbarModel),
                            step = HomeToolbarState.HomeToolbarStateStep.UPDATE_TOOLBAR
                    )
                }
            else
                updateDataState {
                    copy(
                            toolbarModel = updateToolbar.invoke(it.toolbarModel),
                            step = HomeToolbarState.HomeToolbarStateStep.UPDATE_TOOLBAR
                    )
                }
        }
    }

    fun onActionSearchViewText(searchViewText: String) {
        notifySingleEvent(EmaExtraData(extraData = searchViewText))
    }

    fun onActionBackClicked() {
        navigate(HomeNavigator.Navigation.Back)
    }

    fun onActionTelegramClicked() {
        navigate(HomeNavigator.Navigation.Telegram)
    }

    fun onActionSearchClick() {
        navigate(HomeNavigator.Navigation.Search)
    }

    fun onActionShowInterstitialAd() {
        checkDataState {
            updateToNormalState {
                copy(
                        step = HomeToolbarState.HomeToolbarStateStep.SHOW_INTERSTITIAL
                )
            }
        }
    }
}