package com.dd.domain.repository

import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.ResponseCategoryModel

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo</a>
 */

interface Repository {

    suspend fun getCategory(requestCategoryModel: RequestCategoryModel): ResponseCategoryModel

    suspend fun getMakal(requestMakalModel: RequestMakalModel): ResponseMakalModel
}