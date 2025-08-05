package uz.mrsolijon.quizapp.di

import uz.mrsolijon.quizapp.data.remote.api.QuizApi
import uz.mrsolijon.quizapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonConverterFactory: GsonConverterFactory): QuizApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory).build().create(QuizApi::class.java)
    }
}