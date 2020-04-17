package com.dd.database.sqlite.ui.home

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.carmabs.ema.android.extra.EmaReceiverModel
import com.carmabs.ema.android.extra.EmaResultModel
import com.carmabs.ema.android.ui.EmaView
import com.carmabs.ema.core.constants.FLOAT_ZERO
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
        if (checkToolbarVisibility(data)) {
            updateToolbar(data.toolbarModel)
        }
    }

    /**
     * Customs functions
     */
    private fun setupToolbar(viewModel: MainToolbarsViewModel) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val backVisibility = destination.id != R.id.categoryViewFragment
            viewModel.onActionUpdateToolbar(false) {
                it.copy(
                        backVisibility = backVisibility,
                        title = destination.label?.toString() ?: STRING_EMPTY
                )
            }
        }
        clToolbarBack.setOnClickListener { viewModel.onActionBackClicked() }
        ivToolbarCloseSession.setOnClickListener { viewModel.onActionCloseSessionClicked() }
    }

    private fun checkToolbarVisibility(data: HomeToolbarState): Boolean {
        val visibility = data.toolbarModel.visibility
        val gone = data.toolbarModel.gone

        if (visibility)
            showToolbar()
        else
            hideToolbar(gone)

        return visibility
    }

    private fun updateToolbar(data: ToolbarModel) {
        val title = data.title
        val backVisibility = if (data.backVisibility) View.VISIBLE else View.INVISIBLE
        val closeSessionVisibility = if (data.closeSessionVisibility) View.VISIBLE else View.INVISIBLE
        if (title.isEmpty()) {
            tvToolbarTitle.visibility = View.GONE
            ivToolbarLogo.visibility = View.VISIBLE
        } else {
            ivToolbarLogo.visibility = View.GONE
            tvToolbarTitle.visibility = View.VISIBLE
        }

        if (data.elevation) {
            if (ivToolbarLogo.visibility == View.VISIBLE)
                toolbarLayout.elevation = FLOAT_ZERO
            else
                toolbarLayout.elevation = FLOAT_ZERO
        } else
            toolbarLayout.elevation = FLOAT_ZERO

        tvToolbarTitle.text = title
        clToolbarBack.visibility = backVisibility
        ivToolbarCloseSession.visibility = closeSessionVisibility


        data.exitButton?.let { exitButton ->
            tvToolbarExit.visibility = View.VISIBLE
            tvToolbarExit.text = exitButton.text
            if (!exitButton.enabled) {
                tvToolbarExit.isEnabled = false
                tvToolbarExit.isClickable = false
                tvToolbarExit?.setTextColor(resources.getColor(R.color.primary_light, null))
            } else {
                tvToolbarExit.isEnabled = true
                tvToolbarExit.isClickable = true
                tvToolbarExit?.setTextColor(resources.getColor(R.color.colorAccent, null))
            }
            if (exitButton.textSize != FLOAT_ZERO) {
                tvToolbarExit.textSize = exitButton.textSize
            }
            tvToolbarExit.setOnClickListener {
                exitButton.onClickListener.invoke()
            }
        }


        data.backClickListener?.let { listener ->
            clToolbarBack.setOnClickListener { listener.invoke() }
        }

        if (data.backDrawableCross) {
            ivToolbarBack.setImageResource(R.drawable.ic_cross)
        } else {
            ivToolbarBack.setImageResource(R.drawable.ic_keyboard_arrow_left_black_24dp)
        }
    }
}