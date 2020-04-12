package com.dd.data.net.model


import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.ResponseCategoryModel

fun ResponseCategoryApi.toDomainModel(): ResponseCategoryModel = ResponseCategoryModel(
        result = this.result
)

fun RequestCategoryModel.toDataModel(): RequestCategoryApi = RequestCategoryApi(
        default = this.default
)