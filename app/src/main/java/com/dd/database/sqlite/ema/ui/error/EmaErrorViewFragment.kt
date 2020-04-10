package com.dd.database.sqlite.ema.ui.error

import android.view.View
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.ema.base.BaseFragment
import org.kodein.di.generic.instance

class EmaErrorViewFragment : BaseFragment<EmaErrorState, EmaErrorViewModel, EmaErrorNavigator.Navigation>() {

    /**
     * If you wouldn't want to use dependency injection you can provide it instantiating the class.
     * Not recommended
     */
    override val viewModelSeed: EmaErrorViewModel = EmaErrorViewModel()

    private val toolbarViewModelSeed: EmaErrorToolbarViewModel by instance()

    override val navigator: EmaErrorNavigator by instance()

    override fun onInitialized(viewModel: EmaErrorViewModel) {
        viewModel.toolbarViewModel = addExtraViewModel(toolbarViewModelSeed, this, requireActivity()) {
            viewModel.onToolbarUpdated(it)
        }
        setupButtons(viewModel)
    }

    private fun setupButtons(viewModel: EmaErrorViewModel) {
        bErrorToolbar.setOnClickListener { viewModel.onActionToolbar() }
        bErrorAddUser.setOnClickListener { viewModel.onActionAddUser() }
    }

    override fun onNormal(data: EmaErrorState) {
        checkShowToolbarTriggerVisibility(data)
    }

    private fun checkShowToolbarTriggerVisibility(data: EmaErrorState) {
        bErrorToolbar.visibility = if (data.showToolbarViewVisibility) View.VISIBLE else View.GONE
    }

    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onSingle(data: EmaExtraData) {
    }

    override fun onError(error: Throwable) {
    }

    override val layoutId: Int = com.dd.database.sqlite.R.layout.fragment_error
}