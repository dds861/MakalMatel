package com.dd.database.sqlite.ui.makal

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_makal.*
import org.kodein.di.generic.instance

class MakalViewFragment
    : BaseFragment<MakalState, MakalViewModel, MakalNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = R.layout.fragment_makal
    override val navigator: MakalNavigator by instance()
    override val viewModelSeed: MakalViewModel by instance()

    /**
     * Default functions
     */
    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onNormal(data: MakalState) {
        loadRecyclerViews(data)
    }

    override fun onError(error: Throwable) {}

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onSingle(data: EmaExtraData) {
    }

    override fun onInitialized(viewModel: MakalViewModel) {
        setupRecycler()
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

