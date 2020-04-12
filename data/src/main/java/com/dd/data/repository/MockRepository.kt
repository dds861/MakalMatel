package com.dd.data.repository


import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.model.RequestLogin
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.ResponseCategoryModel
import com.dd.domain.model.ResponseUser
import com.dd.domain.repository.Repository
import kotlinx.coroutines.delay
import javax.security.auth.login.LoginException


/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Created by: Carlos Mateo Benito on 19/1/19.
 */
class MockRepository : Repository {

    override suspend fun login(requestLogin: RequestLogin): ResponseUser {
        delay(2000)
        if (requestLogin.name.equals("Admin", true) && requestLogin.password == "1234")
            return ResponseUser("Admin", "EMA based on MVVM Architecture, powered by Carmabs")
        else throw LoginException()
    }


    override suspend fun getCategory(requestCategoryModel: RequestCategoryModel): ResponseCategoryModel {
        return ResponseCategoryModel(
                result = "AnyText"
        )
    }

    override suspend fun getMakal(requestMakalModel: RequestMakalModel): ResponseMakalModel {
        return ResponseMakalModel(
                result = "AnyText"
        )
    }
}