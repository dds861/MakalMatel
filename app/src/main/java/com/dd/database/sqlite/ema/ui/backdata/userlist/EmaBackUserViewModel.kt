package com.dd.database.sqlite.ema.ui.backdata.userlist;

import com.dd.database.sqlite.ema.base.BaseViewModel
import com.dd.database.sqlite.ema.ui.backdata.EmaBackNavigator
import com.dd.database.sqlite.ema.ui.backdata.creation.EmaBackUserCreationViewModel

/**
 *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 * <p>
 * Date: 2019-11-07
 */

class EmaBackUserViewModel : BaseViewModel<EmaBackUserState, EmaBackNavigator.Navigation>() {

    companion object {
        const val RESULT_USER_NUMBER = 1000
    }

    override val initialViewState: EmaBackUserState = EmaBackUserState()


    override fun onStartFirstTime(statePreloaded: Boolean) {

    }

    override fun onResultListenerSetup() {
        addOnResultReceived(EmaBackUserCreationViewModel.RESULT_USER){
            updateToNormalState {
                val mutableList = listUsers.toMutableList()
                mutableList.add(it.data as EmaBackUserModel)
                addResult(mutableList.size, RESULT_USER_NUMBER)
                copy(listUsers = mutableList,noUserVisibility = mutableList.isEmpty())
            }
        }
    }

    fun onActionAddUser() {
        navigate(EmaBackNavigator.Navigation.Result)
    }


}
