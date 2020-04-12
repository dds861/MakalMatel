package com.dd.database.sqlite.ui.category

import com.dd.database.sqlite.base.BaseViewModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.usecase.GetCategoryUseCase


class CategoryViewModel(
        val resourceManager: ResourceManager,
        private val getCategoryUseCase: GetCategoryUseCase
) : BaseViewModel<CategoryState, CategoryNavigator.Navigation>() {




    override val initialViewState: CategoryState = CategoryState()

    fun onActionUpdate(text: String) {
//        executeSessionUseCaseWithException({
//            val result = getCategoryUseCase.execute(
//                    RequestCategoryModel(
//                            default = text
//                    )
//            )
//        }, { e ->
//            updateToErrorState(e)
//        })
    }

    override fun onStartFirstTime(statePreloaded: Boolean) {

    }
}