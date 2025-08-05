package uz.mrsolijon.quizapp.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mrsolijon.quizapp.repository.QuizRepository
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: QuizRepository
) : ViewModel() {

    val scoreFlow = repository.getScoreDataById()

}