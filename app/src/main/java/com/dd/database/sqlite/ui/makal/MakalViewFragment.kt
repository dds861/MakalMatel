package com.dd.database.sqlite.ui.makal

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseToolbarsFragment
import com.dd.database.sqlite.ui.category.CategoryViewModel
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel
import kotlinx.android.synthetic.main.fragment_makal.*
import org.kodein.di.generic.instance

class MakalViewFragment
    : BaseToolbarsFragment<MakalState, MakalViewModel, MakalNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = R.layout.fragment_makal
    override val navigator: MakalNavigator by instance()
    override val viewModelSeed: MakalViewModel by instance()

    /**
     * Custom variables
     */
    private lateinit var vm: MakalViewModel

    /**
     * Default functions
     */
    override fun onInitializedWithToolbarsManagement(viewModel: MakalViewModel, mainToolbarViewModel: MainToolbarsViewModel) {
        vm = viewModel
        setupRecycler()
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onNormal(data: MakalState) {
        loadRecyclerViews(data)
    }

    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onError(error: Throwable) {}

    override fun onSingle(data: EmaExtraData) {
    }

    /**
     * Custom functions
     */
    private fun setupRecycler() {
        rvMakals.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: MakalState) {
        rvMakals.adapter = data.listMakals.toMutableList().let { it ->
            MakalAdapter(requireContext(), listItems = it) {
                viewModelSeed.onActionItemClicked(it)
            }
        }
    }


}

