package com.example.test908.network
import com.example.test908.BuildConfig
import com.example.test908.data.repository.remoteData
import com.example.test908.utils.constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : serviceRetrofit =
             Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(provideBaseUrl())
            .client(okHttpClient)
            .build()
                 .create(serviceRetrofit::class.java)
    @Provides
    @Singleton
    fun provideBaseUrl() = constant.BASE_URL
    @Singleton
    @Provides
    fun provideRemote(ser: serviceRetrofit): remoteData = remoteData(ser)

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }
}