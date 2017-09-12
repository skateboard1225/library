package com.skateboard.library.network

import com.skateboard.library.network.convert.ResultConvertFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by skateboard on 2017/8/29.
 */
object RetrofitManager {

    private var retrofit:Retrofit

    init {

        var httpClient: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        var builder=Retrofit.Builder().baseUrl("http://peopleforce.app/")
                .client(httpClient)
                .addConverterFactory(ResultConvertFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofit=builder.build()

    }


    fun <T> getData(service:Class<T>):T
    {
        return retrofit.create(service)
    }

    fun <T> observer(observer: Observable<T>): Observable<T>
    {

        return observer.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}