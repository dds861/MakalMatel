package com.dd.data.repository

import android.content.Context
import android.os.Build
import com.carmabs.ema.core.concurrency.ConcurrencyManager
import com.dd.data.net.API
import com.dd.data.net.model.toDataModel
import com.dd.data.net.model.toDomainModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.LoginRequest
import com.dd.domain.model.RequestCategoryModel
import com.dd.domain.model.ResponseCategoryModel
import com.dd.domain.model.User
import com.dd.domain.repository.Repository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.xml.sax.ErrorHandler
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import java.util.logging.Logger


class ApiLdaRepository() : Repository {

    private lateinit var api: API

    /**-------------------------------------------------------------------------------------------*/
    /**                                                                                           */
    /**                                  RETROFIT SERVICES                                        */
    /**                                                                                           */
    /**-------------------------------------------------------------------------------------------*/

    private var retrofit: Retrofit = createRetrofit()
        set(value) {
            field = value
            api = field.create(API::class.java)
        }

    init {
        retrofit = createRetrofit()
    }


    private fun createRetrofit(token: String? = null): Retrofit {

        return Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**-------------------------------------------------------------------------------------------*/
    /**                                                                                           */
    /**                                IMPLEMENTATIONS                                            */
    /**                                                                                           */
    /**-------------------------------------------------------------------------------------------*/

    override suspend fun login(loginRequest: LoginRequest): User {
        return User()
    }


    override suspend fun getCategory(requestCategoryModel: RequestCategoryModel): ResponseCategoryModel {
        return api.getCategory(requestCategoryModel.toDataModel()).await().toDomainModel()
    }
}