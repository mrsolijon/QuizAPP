package uz.mrsolijon.quizapp.di

import uz.mrsolijon.quizapp.repository.ProdQuizRepository
import uz.mrsolijon.quizapp.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindQuizRepository(repository: ProdQuizRepository): QuizRepository
}