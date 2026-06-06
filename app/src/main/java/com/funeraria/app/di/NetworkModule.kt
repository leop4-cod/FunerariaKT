package com.funeraria.app.di

import com.funeraria.app.BuildConfig
import com.funeraria.app.data.remote.api.*
import com.funeraria.app.data.remote.interceptors.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

        if (BuildConfig.DEBUG) {
            val trustAll = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(c: Array<out X509Certificate>?, a: String?) {}
                override fun checkServerTrusted(c: Array<out X509Certificate>?, a: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })
            val ssl = SSLContext.getInstance("SSL").apply { init(null, trustAll, SecureRandom()) }
            builder.sslSocketFactory(ssl.socketFactory, trustAll[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
        }
        return builder.build()
    }

    @Provides @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides @Singleton fun provideAuthApi(r: Retrofit):     AuthApi     = r.create(AuthApi::class.java)
    @Provides @Singleton fun provideClienteApi(r: Retrofit):  ClienteApi  = r.create(ClienteApi::class.java)
    @Provides @Singleton fun provideDifuntoApi(r: Retrofit):  DifuntoApi  = r.create(DifuntoApi::class.java)
    @Provides @Singleton fun provideProductoApi(r: Retrofit): ProductoApi = r.create(ProductoApi::class.java)
    @Provides @Singleton fun provideServicioApi(r: Retrofit): ServicioApi = r.create(ServicioApi::class.java)
    @Provides @Singleton fun provideContratoApi(r: Retrofit): ContratoApi = r.create(ContratoApi::class.java)
    @Provides @Singleton fun provideEmpleadoApi(r: Retrofit): EmpleadoApi = r.create(EmpleadoApi::class.java)
    @Provides @Singleton fun provideProveedorApi(r: Retrofit):ProveedorApi= r.create(ProveedorApi::class.java)
    @Provides @Singleton fun providePagoApi(r: Retrofit):     PagoApi     = r.create(PagoApi::class.java)
}
