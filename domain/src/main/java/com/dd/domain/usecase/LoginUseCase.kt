package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestLogin
import com.dd.domain.model.ResponseUser
import com.dd.domain.repository.Repository


/**
 * Login
 *
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo</a>
 */

class LoginUseCase(private val repository: Repository) : EmaUseCase<RequestLogin, ResponseUser>() {

    override suspend fun useCaseFunction(input: RequestLogin): ResponseUser {
        return repository.login(input)
    }
}