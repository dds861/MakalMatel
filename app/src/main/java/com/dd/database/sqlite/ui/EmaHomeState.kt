package com.dd.database.sqlite.ui

import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState

data class EmaHomeState(
        val userName: String = STRING_EMPTY,
        val userPassword: String = STRING_EMPTY,
        val showPassword: Boolean = false,
        val rememberUser: Boolean = false
) : EmaBaseState