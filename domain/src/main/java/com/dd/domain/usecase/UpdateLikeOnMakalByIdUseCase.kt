package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.repository.Repository

class UpdateLikeOnMakalByIdUseCase(private val repository: Repository.LocalStorageRepository)
    : EmaUseCase<RequestMakalModel, ResponseMakalModel>() {
    override suspend fun useCaseFunction(input: RequestMakalModel): ResponseMakalModel {
        return repository.updateLikeOnMakalById(input)
    }
}


