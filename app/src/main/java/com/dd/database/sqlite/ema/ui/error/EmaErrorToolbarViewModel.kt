package com.dd.database.sqlite.ema.ui.error

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.ema.base.BaseViewModel
import com.dd.database.sqlite.ema.ui.backdata.userlist.EmaBackUserViewModel

/**
 *
 *
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo</a>
 */

class EmaErrorToolbarViewModel : BaseViewModel<EmaErrorToolbarState, EmaErrorNavigator.Navigation>() {

    override val initialViewState: EmaErrorToolbarState = EmaErrorToolbarState()

    override fun onStartFirstTime(statePreloaded: Boolean) {

    }

    override fun onResultListenerSetup() {
        addOnResultReceived(EmaBackUserViewModel.RESULT_USER_NUMBER) {
            notifySingleEvent(EmaExtraData(extraData = it.data as Int))
        }
    }

    fun onActionMenuHideToolbar() {
        hideToolbar()
    }

    fun showToolbar() {
        updateToNormalState {
            copy(visibility = true)
        }
    }

    fun hideToolbar() {
        updateToNormalState {
            copy(visibility = false)
        }
    }
}