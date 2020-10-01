package com.dd.database.sqlite.ui.search

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.data.utils.KeyboardUtils
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseToolbarsFragment
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.kodein.di.generic.instance

class SearchViewFragment
    : BaseToolbarsFragment<SearchState, SearchViewModel, SearchNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = R.layout.fragment_search
    override val navigator: SearchNavigator by instance()
    override val viewModelSeed: SearchViewModel by instance()

    /**
     * Custom variables
     */
    private lateinit var vm: SearchViewModel

    /**
     * Default functions
     */
    override fun onInitializedWithToolbarsManagement(viewModel: SearchViewModel, mainToolbarViewModel: MainToolbarsViewModel) {
        vm = viewModel
        setupRecycler()
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onNormal(data: SearchState) {
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
        rvSearch.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: SearchState) {
        rvSearch.adapter = data.listMakals.toMutableList().let { mutableList ->
            SearchAdapter(context = requireContext(), listItems = mutableList, adapterType = data.adapterType) { makalModel ->
                when (data.adapterType) {
                    SearchState.AdapterType.HINT -> {
                        vm.onActionFilterToolbarMakalsByQueryText(makalModel.makal_text)
                        activity?.let { KeyboardUtils.hideKeyboard(it, view) }
                    }

                    SearchState.AdapterType.MAKALS -> {
                    }
                }
            }
        }
    }
}

