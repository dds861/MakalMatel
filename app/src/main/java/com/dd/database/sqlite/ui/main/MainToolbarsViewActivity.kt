package com.dd.database.sqlite.ui.main

import android.os.Bundle
import android.view.View
import com.carmabs.ema.android.extra.EmaReceiverModel
import com.carmabs.ema.android.extra.EmaResultModel
import com.carmabs.ema.android.ui.EmaView
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
    lateinit var vm: MainToolbarsViewModel

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
        vm = viewModel
        ivToolbarTelegram.setOnClickListener { viewModel.onActionTelegramClicked() }
        ivToolbarSearch.setOnClickListener { viewModel.onActionSearchClick() }
    }

    private fun updateToolbar(data: ToolbarModel) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id == R.id.categoryViewFragment) {
                true -> {
                    ivToolbarLogoOrBack.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher_pen, null))
                    ivToolbarLogoOrBack.setOnClickListener { null }
                }

                false -> {
                    ivToolbarLogoOrBack.setImageDrawable(resources.getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp, null))
                    ivToolbarLogoOrBack.setOnClickListener { vm.onActionBackClicked() }
                }
            }
        }


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

        data.toolbarLogoOrBackVisibility.let {
            if (it) {
                ivToolbarLogoOrBack.visibility = View.VISIBLE
            } else {
                ivToolbarLogoOrBack.visibility = View.GONE
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
        }

        data.searchButton?.let { searchButton ->
            if (searchButton.visibility) {
                ivToolbarSearch.visibility = View.VISIBLE
            } else {
                ivToolbarSearch.visibility = View.GONE
            }
        }
    }
}