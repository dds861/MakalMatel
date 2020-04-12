package com.dd.database.sqlite.ui.category

import com.dd.database.sqlite.base.BaseViewModel
import com.dd.domain.manager.ResourceManager


class CategoryViewModel(private val resourceManager: ResourceManager) :
        BaseViewModel<CategoryState, CategoryNavigator.Navigation>() {


    override val initialViewState: CategoryState = CategoryState()

    override fun onStartFirstTime(statePreloaded: Boolean) {
        val list: List<CategoryModel> = resourceManager.getCategoryList().map { CategoryModel(title = it) }

        updateToNormalState {
            copy(
                    listCategories = list
            )
        }
    }

    fun onActionCategoryClick(it: CategoryModel) {

    }
}