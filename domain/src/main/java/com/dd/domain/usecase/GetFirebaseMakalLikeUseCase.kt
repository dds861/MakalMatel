package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.repository.Repository

class GetFirebaseMakalLikeUseCase(private val repository: Repository.FirebaseRepository)
    : EmaUseCase<RequestMakalModel, Unit>() {
    override suspend fun useCaseFunction(input: RequestMakalModel) {
        repository.onLikeClicked(input)
    }
}