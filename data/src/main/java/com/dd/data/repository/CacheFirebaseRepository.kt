package com.dd.data.repository

import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.repository.Repository

class CacheFirebaseRepository(private val repository: Repository.FirebaseRepository) : Repository.FirebaseRepository {
    override suspend fun writeToDb(requestMakalModel: RequestMakalModel): ResponseMakalModel {
        return repository.writeToDb(requestMakalModel)
    }

    override suspend fun readFromDb(requestMakalModel: RequestMakalModel): ResponseMakalModel {
        return repository.readFromDb(requestMakalModel)
    }
}

