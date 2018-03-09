package olaskorp.tinkoffnews.utils

import olaskorp.tinkoffnews.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by
 * tery on 06.03.2018.
 */

fun Retrofit.Builder.buildDefaultInstance(): Retrofit = this.baseUrl(BuildConfig.REMOTE_HOST)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()