package com.dd.injection

import com.dd.data.manager.AndroidResourceManager
import com.dd.data.repository.MockRepository
import com.dd.domain.manager.ResourceManager
import com.dd.domain.repository.Repository
import com.dd.domain.usecase.LoginUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Created by: Carlos Mateo Benito on 20/1/19.
 */

fun appDataInjection() = Kodein.Module(name = "AppDataModule") {

   bind<Repository>() with singleton { MockRepository() }

   bind<LoginUseCase>() with provider { LoginUseCase(instance()) }

   bind<ResourceManager>() with singleton { AndroidResourceManager(instance()) }
}