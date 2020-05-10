package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.repository.Repository

class UpdateFirebaseMakalLikeUseCase(private val repository: Repository.FirebaseRepository)
    : EmaUseCase<RequestMakalModel, ResponseMakalModel>() {
    override suspend fun useCaseFunction(input: RequestMakalModel): ResponseMakalModel {
        return repository.writeToDb(input)
    }
}