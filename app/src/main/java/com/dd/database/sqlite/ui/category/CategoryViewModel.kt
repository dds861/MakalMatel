package com.dd.database.sqlite.ui.category

import com.dd.database.sqlite.base.BaseToolbarsViewModel
import com.dd.database.sqlite.model.ToolbarModel
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel
import com.dd.database.sqlite.ui.makal.MakalState
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.CategoryModel
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.usecase.GetLocalCategoryUseCase
import java.util.*

class CategoryViewModel(
        private val resourceManager: ResourceManager,
        private val getLocalCategoryUseCase: GetLocalCategoryUseCase
) : BaseToolbarsViewModel<CategoryState, CategoryNavigator.Navigation>() {
    /**
     * Constants
     */
    companion object {
        const val TELEGRAM_CLICKED = 1
    }

    /**
     * Default variables
     */
    override val initialViewState: CategoryState = CategoryState()
    /**
     * Custom variables
     */
    /**
     * Default functions
     */
    override fun onConfigureToolbars(mainToolbarsVm: MainToolbarsViewModel) {
        mainToolbarsVm.onActionUpdateToolbar {
            it.copy(
                    toolbarTitle = resourceManager.getToolbarTitle(),
                    toolbarTitleVisibility = true,
                    toolbarLogoOrBackVisibility = true,
                    telegramButton = ToolbarModel.TelegramButton(
                            visibility = true
                    ),
                    searchButton = ToolbarModel.SearchButton(
                            visibility = true
                    )
            )
        }
    }

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

    /**
     * Custom functions
     */
    fun onActionCategoryClick(categoryModel: CategoryModel) {
        navigate(
                CategoryNavigator.Navigation.Makal(
                        MakalState(
                                categoryId = categoryModel.category_id,
                                categoryTitle = categoryModel.category_text
                        )
                )
        )
    }

    fun onActionSearch(queryText: String) {
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseCategoryModel = getLocalCategoryUseCase.execute(RequestCategoryModel())
                        updateToNormalState {
                            copy(
                                    listCategories = responseCategoryModel.list.filter {
                                        it.category_text
                                                .toLowerCase(Locale.ROOT)
                                                .contains(queryText.toLowerCase(Locale.ROOT))
                                    }
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }
}