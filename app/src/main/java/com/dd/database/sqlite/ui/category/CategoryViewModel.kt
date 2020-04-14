package com.dd.database.sqlite.ui.category

import com.dd.database.sqlite.base.BaseViewModel
import com.dd.database.sqlite.ui.makal.MakalState
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.CategoryModel
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.usecase.GetLocalCategoryUseCase


class CategoryViewModel(
        private val resourceManager: ResourceManager,
        private val getLocalCategoryUseCase: GetLocalCategoryUseCase
) : BaseViewModel<CategoryState, CategoryNavigator.Navigation>() {


    override val initialViewState: CategoryState = CategoryState()

    override fun onStartFirstTime(statePreloaded: Boolean) {
        executeUseCaseWithException(
                {
                    val responseCategoryModel= getLocalCategoryUseCase.execute(RequestCategoryModel())
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
                                categoryId = categoryModel.category_id
                        )
                )
        )
    }
}