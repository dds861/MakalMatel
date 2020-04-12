package com.dd.data.repository

import com.dd.domain.model.LoginRequest
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.ResponseCategoryModel
import com.dd.domain.model.User
import com.dd.domain.repository.Repository

class CacheApiRepository(private val repository: Repository) : Repository {
    override suspend fun login(loginRequest: LoginRequest): User {
        return repository.login(loginRequest)
    }

    override suspend fun getCategory(requestCategoryModel: RequestCategoryModel): ResponseCategoryModel {
        return repository.getCategory(requestCategoryModel)
    }
}