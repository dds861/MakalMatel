package com.dd.database.sqlite.ui.main

import android.os.Bundle
import android.view.View
import com.carmabs.ema.android.extra.EmaReceiverModel
import com.carmabs.ema.android.extra.EmaResultModel
import com.carmabs.ema.android.ui.EmaView
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseActivity
import com.dd.database.sqlite.model.ToolbarModel
import kotlinx.android.synthetic.main.toolbar.*
import org.kodein.di.generic.instance

class MainToolbarsViewActivity : BaseActivity(), EmaView<HomeToolbarState, MainToolbarsViewModel, HomeNavigator.Navigation> {
    /**
     * Default variables
     */
    override val inputState: HomeToolbarState? = null
    override var previousState: HomeToolbarState? = null
    override val viewModelSeed: MainToolbarsViewModel by instance()
    override val navigator: HomeNavigator by instance()
    override val navGraph: Int = R.navigation.navigation_ema_home
    /**
     * Customs variables
     */
    /**
     * Default functions
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel(this)
    }

    override fun onResultReceiverInvokeEvent(emaReceiverModel: EmaReceiverModel) {
    }

    override fun onResultSetEvent(emaResultModel: EmaResultModel) {
    }

    override fun onViewModelInitialized(viewModel: MainToolbarsViewModel) {
        setupToolbar(viewModel)
    }

    override fun provideFixedToolbarTitle(): String? = null

    override fun onStateAlternative(data: EmaExtraData) {
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onStateError(error: Throwable) {
    }

    override fun onStateNormal(data: HomeToolbarState) {
        updateToolbar(data.toolbarModel)
    }

    /**
     * Customs functions
     */
    private fun setupToolbar(viewModel: MainToolbarsViewModel) {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            val backVisibility = destination.id != R.id.categoryViewFragment
//            viewModel.onActionUpdateToolbar(false) {
//                it.copy(
//                        backButton = ToolbarModel.BackButton(visibility = backVisibility, onClickListener = {viewModel.onActionBackClicked()}),
//                        toolbarTitle = destination.label?.toString() ?: STRING_EMPTY
//                )
//            }
//        }
//        ivToolbarBack.setOnClickListener { viewModel.onActionBackClicked() }
//        ivToolbarTelegram.setOnClickListener { viewModel.onActionCloseSessionClicked() }

        ivToolbarBack.setOnClickListener {
            viewModel.onActionBackClicked()
        }
    }

    private fun updateToolbar(data: ToolbarModel) {
        data.toolbarTitle.let {
            tvToolbarTitle.text = data.toolbarTitle
        }

        data.toolbarTitleVisibility.let {
            if (it) {
                tvToolbarTitle.visibility = View.VISIBLE
            } else {
                tvToolbarTitle.visibility = View.GONE
            }
        }

        data.toolbarLogoVisibility.let {
            if (it) {
                ivToolbarLogo.visibility = View.VISIBLE
            } else {
                ivToolbarLogo.visibility = View.GONE
            }
        }

        data.toolbarVisibility.let {
            if (it) {
                showToolbar()
                constraintToolbar.visibility = View.VISIBLE
            } else {
                hideToolbar()
                constraintToolbar.visibility = View.GONE
            }
        }

        data.telegramButton?.let { telegramButton ->
            if (telegramButton.visibility) {
                ivToolbarTelegram.visibility = View.VISIBLE
            } else {
                ivToolbarTelegram.visibility = View.GONE
            }

            ivToolbarTelegram.setOnClickListener {
                telegramButton.onClickListener.invoke()
            }
        }

        data.searchButton?.let { searchButton ->
            if (searchButton.visibility) {
                ivToolbarSearch.visibility = View.VISIBLE
            } else {
                ivToolbarSearch.visibility = View.GONE
            }

            ivToolbarSearch.setOnClickListener {
                searchButton.onClickListener.invoke()
            }
        }

        data.backButton?.let { backButton ->
            if (backButton.visibility) {
                ivToolbarBack.visibility = View.VISIBLE
            } else {
                ivToolbarBack.visibility = View.GONE
            }


        }
    }
}