package com.dd.domain.repository

import com.dd.domain.model.ResponseCategoryModel
import com.dd.domain.model.ResponseMakalModel

/**
 * Repository to save local data in app
 *
 * <p>
 * Copyright (c) 2019, Babel Sistemas de Información. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:carlos.mateo@babel.es”>Carlos Mateo</a>
 */

interface LocalStorageRepository {

    fun getAllCategories(responseCategoryModel: ResponseCategoryModel)

    fun getAllMakals(responseMakalModel: ResponseMakalModel)
}