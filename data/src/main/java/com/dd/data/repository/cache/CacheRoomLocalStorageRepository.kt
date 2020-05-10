package com.dd.data.repository.cache

import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.ResponseCategoryModel
import com.dd.domain.repository.Repository

class CacheRoomLocalStorageRepository(private val repository: Repository.LocalStorageRepository) : Repository.LocalStorageRepository {
    override suspend fun getAllCategories(request: RequestCategoryModel): ResponseCategoryModel {
        return repository.getAllCategories(request)
    }

    override suspend fun getAllMakals(request: RequestMakalModel): ResponseMakalModel {
        return repository.getAllMakals(request)
    }

    override suspend fun getMakalsByCategoryId(request: RequestMakalModel): ResponseMakalModel {
        return repository.getMakalsByCategoryId(request)
    }

    override suspend fun getMakalsByQueryText(request: RequestMakalModel): ResponseMakalModel {
        return repository.getMakalsByQueryText(request)
    }

    override suspend fun getRandomMakal(): ResponseMakalModel {
        return repository.getRandomMakal()
    }

    override suspend fun setLikeOnMakalById(requestMakalModel: RequestMakalModel): ResponseMakalModel {
        return repository.setLikeOnMakalById(requestMakalModel)
    }
}

