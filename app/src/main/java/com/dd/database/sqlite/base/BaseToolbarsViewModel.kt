package com.dd.database.sqlite.base

import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel

abstract class BaseToolbarsViewModel<T, NS : EmaNavigationState> : BaseViewModel<T, NS>() {
    lateinit var mainToolbarsVm: MainToolbarsViewModel

    override fun onResume(firstTime: Boolean) {
        super.onResume(firstTime)
        onConfigureToolbars(mainToolbarsVm)
    }

    abstract fun onConfigureToolbars(mainToolbarsVm: MainToolbarsViewModel)
}