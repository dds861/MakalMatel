package com.dd.database.sqlite.ui.category

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseFragment
import org.kodein.di.generic.instance

class CategoryViewFragment
    : BaseFragment<CategoryState, CategoryViewModel, CategoryNavigator.Navigation>() {

    /**
     * Default variables
     */

    override val layoutId: Int = R.layout.fragment_category

    override val navigator: CategoryNavigator by instance()

    override val viewModelSeed: CategoryViewModel by instance()

    /**
     * Default functions
     */

    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onNormal(data: CategoryState) {
    }

    override fun onError(error: Throwable){

    }


    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onSingle(data: EmaExtraData) {
        TODO("Not yet implemented")
    }

    override fun onInitialized(viewModel: CategoryViewModel) {
    }

}
