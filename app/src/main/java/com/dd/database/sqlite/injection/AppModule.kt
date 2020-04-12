package com.dd.database.sqlite.injection

import android.app.Application
import com.dd.domain.usecase.GetCategoryUseCase
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

fun appInjection(application: Application) = Kodein.Module(name = "AppModule") {

    bind<Application>() with singleton { application }


    bind<GetCategoryUseCase>() with provider { GetCategoryUseCase(instance()) }

}