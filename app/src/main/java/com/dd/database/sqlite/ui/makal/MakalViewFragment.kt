package com.dd.database.sqlite.ui.makal

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseFragment
import org.kodein.di.generic.instance
import com.dd.database.sqlite.R
import com.dd.database.sqlite.ui.category.CategoryAdapter
import com.dd.database.sqlite.ui.category.CategoryState
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_makal.*


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
        recyclerview2.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: MakalState) {
        recyclerview2.adapter = data.listMakals.toMutableList().let { it ->
            MakalAdapter(listItems = it) {
                viewModelSeed.onActionCopyClick(it)
            }
        }
    }
}
