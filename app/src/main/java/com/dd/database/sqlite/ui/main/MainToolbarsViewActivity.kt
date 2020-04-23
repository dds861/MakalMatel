package com.dd.database.sqlite.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import com.carmabs.ema.android.extra.EmaReceiverModel
import com.carmabs.ema.android.extra.EmaResultModel
import com.carmabs.ema.android.ui.EmaView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseActivity
import com.dd.database.sqlite.model.ToolbarModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_base.*
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
    lateinit var txtSearch: EditText
    private lateinit var adsView: AdView
    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)
            val density = outMetrics.density
            var adWidthPixels = adView.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
        }

    /**
     * Default functions
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel(this)
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this)

        adsView = AdView(this)
        adView.addView(adsView)
        loadBanner()
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
        if (txtSearch.text.toString() != data.extraData.toString()) {
            txtSearch.setText(data.extraData.toString())
        }
    }

    override fun onStateError(error: Throwable) {
    }

    override fun onStateNormal(data: HomeToolbarState) {
        updateToolbar(data.toolbarModel)
    }

    /**
     * Customs functions
     */
    private fun loadBanner() {
        adsView.adUnitId = resources.getString(R.string.banner_ad_unit_id)

        adsView.adSize = adSize
        val adRequest = AdRequest
                .Builder()
                .build()
        adsView.loadAd(adRequest)
    }

    private fun setupToolbar(viewModel: MainToolbarsViewModel) {
        vm = viewModel
        ivToolbarTelegram.setOnClickListener { viewModel.onActionTelegramClicked() }

        ivToolbarSearch.setOnSearchClickListener(View.OnClickListener {
            viewModel.onActionSearchClick()
        })
        txtSearch = ivToolbarSearch.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        txtSearch.hint = resources.getString(R.string.hint_search)
        txtSearch.setHintTextColor(Color.LTGRAY)
        txtSearch.setTextColor(Color.WHITE)
    }

    private fun updateToolbar(data: ToolbarModel) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id == R.id.categoryViewFragment) {
                //Logo pressed
                true -> {
                    ivToolbarLogoOrBack.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher_pen, null))
                    ivToolbarLogoOrBack.setOnClickListener { }
                }
                //Back pressed
                false -> {
                    ivToolbarLogoOrBack.setImageDrawable(resources.getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp, null))
                    ivToolbarLogoOrBack.setOnClickListener {
                        //hide/show searchView on Back press
                        ivToolbarSearch.onActionViewCollapsed()

                        vm.onActionBackClicked()
                    }
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
            ivToolbarSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    searchButton.setOnQueryTextFocusChangeListener?.invoke(query.toLowerCase())
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    searchButton.setOnQueryTextFocusChangeListener?.invoke(newText.toLowerCase())
                    return false
                }
            })



            if (searchButton.visibility) {
                ivToolbarSearch.visibility = View.VISIBLE
            } else {
                ivToolbarSearch.visibility = View.GONE
            }
        }
    }
}