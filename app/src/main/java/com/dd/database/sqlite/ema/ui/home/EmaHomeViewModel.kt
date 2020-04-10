package com.dd.database.sqlite.ema.ui.home

import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.ema.base.BaseViewModel
import com.dd.database.sqlite.ema.ui.user.EmaUserState
import com.dd.domain.exception.UserEmptyException
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.LoginRequest
import com.dd.domain.usecase.LoginUseCase

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Created by: Carlos Mateo Benito on 20/1/19.
 */
class EmaHomeViewModel(
        private val loginUseCase: LoginUseCase,
        private val resourceManager: ResourceManager
) : BaseViewModel<EmaHomeState, EmaHomeNavigator.Navigation>() {

    companion object {
        const val EVENT_MESSAGE = 1000
    }

    override fun onStartFirstTime(statePreloaded: Boolean) {

    }

    override fun onResultListenerSetup() {
        //When two or more resultReceived WITH THE SAME CODE are active, for example in this case,
        //this receiver and the EmaHomeToolbarViewModel receiver, only the last one is executed.
        //ActivityCreated -> EmaHomeToolbarViewModel added -> Fragment created -> EEmaHomeViewModel added->
        //only this result received is executed.


        /* Uncomment to test it

        addOnResultReceived{
            (it.data as? Pair<*, *>)?.also { pair ->
                notifySingleEvent(EmaExtraData(extraData = pair))
            }
        }

        */
    }


    override val initialViewState: EmaHomeState = EmaHomeState()

    private fun doLogin() {
        checkDataState {
            executeUseCaseWithException(
                    {
                        updateToAlternativeState()
                        val user = loginUseCase.execute(LoginRequest(it.userName, it.userPassword))
                        updateToNormalState()
                        notifySingleEvent(EmaExtraData(EVENT_MESSAGE, resourceManager.getCongratulations()))
                        navigate(
                                EmaHomeNavigator.Navigation.User(
                                        EmaUserState(
                                                name = user.name,
                                                surname = user.surname
                                        )
                                )
                        )
                    },
                    { e ->
                        updateToErrorState(e)
                        navigate(EmaHomeNavigator.Navigation.Error)
                    }
            )
        }
    }

    fun onActionLogin() {
        checkDataState {
            when {
                it.userName.isEmpty() -> updateToErrorState(UserEmptyException())
                it.userPassword.isEmpty() -> updateToErrorState(UserEmptyException())
                else -> doLogin()
            }
        }
    }

    fun onActionShowPassword() {
        updateToNormalState {
            copy(showPassword = !showPassword)
        }
    }

    fun onActionRemember(isChecked: Boolean) {
        updateToNormalState {
            copy(rememberUser = isChecked)
        }
    }

    fun onActionDeletePassword() {
        updateToNormalState {
            copy(userPassword = STRING_EMPTY)
        }
    }

    fun onActionDeleteUser() {
        updateDataState  {
            copy(userName = STRING_EMPTY)
        }
    }


    fun onActionUserWrite(user: String) {
        //We only want to update the data of the view without notifying it, it has the edit text updated with
        //text when you write on it, but you need to save the state if for example, there is a device
        //rotation and the view is recreated, to set the text with last value saved on state

        updateDataState {
            copy(userName = user)
        }
    }

    fun onActionPasswordWrite(password: String) {
        updateDataState {
            copy(userPassword = password)
        }
    }

    fun onActionDialogErrorCancel() {
        updateToNormalState()
    }

    fun onActionDialogErrorAccept() {
        updateToNormalState()
    }
}