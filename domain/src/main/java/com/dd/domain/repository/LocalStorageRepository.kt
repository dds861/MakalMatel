package com.dd.domain.repository

import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseCategoryModel
import com.dd.domain.model.ResponseMakalModel

interface LocalStorageRepository {
    suspend fun getAllCategories(request: RequestCategoryModel): ResponseCategoryModel

    suspend fun getAllMakals(request: RequestMakalModel): ResponseMakalModel

    suspend fun getMakalsByCategoryId(request: RequestMakalModel): ResponseMakalModel

    suspend fun getMakalsByQueryText(request: RequestMakalModel): ResponseMakalModel

    suspend fun getRandomMakal(): ResponseMakalModel

    suspend fun setLikeOnMakalById(requestMakalModel: RequestMakalModel): ResponseMakalModel
}