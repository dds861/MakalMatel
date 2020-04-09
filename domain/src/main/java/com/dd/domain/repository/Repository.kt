package com.dd.domain.repository

import com.dd.domain.model.LoginRequest
import com.dd.domain.model.User

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo</a>
 */

interface Repository {

    suspend fun login(loginRequest: LoginRequest): User
}