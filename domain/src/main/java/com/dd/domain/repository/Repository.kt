package com.dd.domain.repository

import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.ResponseCategoryModel


interface Repository {

    suspend fun getCategory(requestCategoryModel: RequestCategoryModel): ResponseCategoryModel

    suspend fun getMakal(requestMakalModel: RequestMakalModel): ResponseMakalModel


    interface FirebaseRepository {
        suspend fun writeToDb(requestMakalModel: RequestMakalModel): ResponseMakalModel
        suspend fun readFromDb(requestMakalModel: RequestMakalModel): ResponseMakalModel
    }

    interface LocalStorageRepository {
        suspend fun getAllCategories(request: RequestCategoryModel): ResponseCategoryModel

        suspend fun getAllMakals(request: RequestMakalModel): ResponseMakalModel

        suspend fun getMakalsByCategoryId(request: RequestMakalModel): ResponseMakalModel

        suspend fun getMakalsByQueryText(request: RequestMakalModel): ResponseMakalModel

        suspend fun getRandomMakal(): ResponseMakalModel

        suspend fun updateLikeOnMakalById(requestMakalModel: RequestMakalModel): ResponseMakalModel
    }
}