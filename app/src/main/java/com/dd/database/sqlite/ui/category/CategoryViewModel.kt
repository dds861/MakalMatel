package com.dd.database.sqlite.ui.category

import com.dd.database.sqlite.base.BaseViewModel
import com.dd.database.sqlite.ui.makal.MakalState
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.CategoryModel
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.usecase.GetLocalCategoryUseCase


class CategoryViewModel(
        private val resourceManager: ResourceManager,
        private val categoryUseCase: GetLocalCategoryUseCase
) : BaseViewModel<CategoryState, CategoryNavigator.Navigation>() {


    override val initialViewState: CategoryState = CategoryState()

    override fun onStartFirstTime(statePreloaded: Boolean) {
//        val list: List<CategoryModel> = resourceManager.getCategoryList().map { CategoryModel(title = it) }

        executeUseCaseWithException(
                {
                    val responseCategoryModel= categoryUseCase.execute(RequestCategoryModel())
                    updateToNormalState {
                        copy(
                                listCategories = responseCategoryModel.list
                        )
                    }
                },
                { e ->
                    updateToErrorState(e)
                })
    }

    fun onActionCategoryClick(categoryModel: CategoryModel) {
        navigate(
                CategoryNavigator.Navigation.Makal(
                        MakalState(
                                categoryIdClicked = categoryModel.category_id
                        )
                )
        )
    }
}