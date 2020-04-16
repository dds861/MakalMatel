package com.dd.database.sqlite.ui.category

import com.carmabs.ema.core.constants.STRING_EMPTY
import com.dd.database.sqlite.base.BaseToolbarsViewModel
import com.dd.database.sqlite.model.ToolbarModel
import com.dd.database.sqlite.ui.home.EmaHomeToolbarViewModel
import com.dd.database.sqlite.ui.makal.MakalState
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.CategoryModel
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.usecase.GetLocalCategoryUseCase

class CategoryViewModel(
        private val resourceManager: ResourceManager,
        private val getLocalCategoryUseCase: GetLocalCategoryUseCase
) : BaseToolbarsViewModel<CategoryState, CategoryNavigator.Navigation>() {
    override val initialViewState: CategoryState = CategoryState()

    override fun onStartFirstTime(statePreloaded: Boolean) {
        executeUseCaseWithException(
                {
                    val responseCategoryModel = getLocalCategoryUseCase.execute(RequestCategoryModel())
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

    override fun onConfigureToolbars(mainToolbarsVm: EmaHomeToolbarViewModel) {
        mainToolbarsVm.onActionUpdateToolbar {
            it.copy(
                    backDrawableCross = false,
                    title = "bye",
                    exitButton = ToolbarModel.ExitButton(
                            text = STRING_EMPTY,
                            onClickListener = {}
                    ),
                    visibility = true,
                    gone = false
            )
        }
    }
}