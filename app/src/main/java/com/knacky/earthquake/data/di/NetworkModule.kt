package com.knacky.earthquake.data.di

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.knacky.earthquake.data.rest.EarthquakeApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.*

@Module
class NetworkModule {

//    private val BASE_URL = "https://40.114.245.6" // dev
//    private val BASE_URL = "https://40.115.9.63" //prod

    private val READ_TIMEOUT = 10

    @Singleton
    @Provides
    internal fun provideOkHttp(@Named("requestLog") requestLog: okhttp3.Interceptor): OkHttpClient {

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        return OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)// remove when http
                .hostnameVerifier { hostname, session -> true } // same
                .addInterceptor(requestLog)
                .build()
    }

    private fun bodyToString(request: Request): String {

        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            if (copy.body() != null) {
                copy.body()!!.writeTo(buffer)
                return buffer.readUtf8()
            } else {
                return "no body"
            }
        } catch (e: IOException) {
            return "did not work"
        }

    }

    @Singleton
    @Provides
    internal fun provideRetrofit(context: Context, client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }


    @Singleton
    @Provides
    @Named("requestLog")
    internal fun provideRequestLogInterceptor(): okhttp3.Interceptor {
        return okhttp3.Interceptor { chain ->
            val request = chain.request().newBuilder().build()
            val response = chain.proceed(request)
            Log.i("onxSomeRequest", "foo")
            Log.i("onxRequestLog", bodyToString(request) + " " + request.url() +
                    " " + response.code() + " " + request.headers().toString())

            response
        }
    }


    @Provides
    @Singleton
    fun provideEarthquakeApi(retrofit: Retrofit): EarthquakeApi {
        return retrofit.create(EarthquakeApi::class.java)
    }


}