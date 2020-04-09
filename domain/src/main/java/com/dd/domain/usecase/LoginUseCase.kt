package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.LoginRequest
import com.dd.domain.model.User
import com.dd.domain.repository.Repository


/**
 * Login
 *
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo</a>
 */

class LoginUseCase(private val repository: Repository) : EmaUseCase<LoginRequest, User>() {

    override suspend fun useCaseFunction(input: LoginRequest): User {
        return repository.login(input)
    }
}