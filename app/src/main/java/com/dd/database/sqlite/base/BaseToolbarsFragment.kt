package com.dd.database.sqlite.base

import com.carmabs.ema.core.navigator.EmaNavigationState
import com.carmabs.ema.core.state.EmaBaseState
import com.dd.database.sqlite.ui.home.EmaHomeToolbarViewModel
import org.kodein.di.generic.instance

abstract class BaseToolbarsFragment<S : EmaBaseState, VM : BaseToolbarsViewModel<S, NS>, NS : EmaNavigationState>
    : BaseFragment<S, VM, NS>() {
    lateinit var mainToolbarsVm: EmaHomeToolbarViewModel
    private val EmaHomeToolbarViewModelSeed: EmaHomeToolbarViewModel by instance()

    override fun onInitialized(viewModel: VM) {
        (viewModel as? BaseToolbarsViewModel<*, *>)?.also {
            mainToolbarsVm = addExtraViewModel(EmaHomeToolbarViewModelSeed, this, requireActivity())
            it.mainToolbarsVm = mainToolbarsVm
            onInitializedWithToolbarsManagement(viewModel, mainToolbarsVm)
        } ?: throw RuntimeException("The view model must be inherited from BaseToolbarsViewModel")
    }

    abstract fun onInitializedWithToolbarsManagement(viewModel: VM, mainToolbarViewModel: EmaHomeToolbarViewModel)
}