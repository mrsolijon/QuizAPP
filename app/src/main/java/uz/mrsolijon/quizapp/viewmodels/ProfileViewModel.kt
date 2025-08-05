package uz.mrsolijon.quizapp.viewmodels

import uz.mrsolijon.quizapp.data.local.entity.UserEntity
import uz.mrsolijon.quizapp.repository.QuizRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    val user = repository.getUserDataById()

    fun insertUserData(userEntity: UserEntity) {
        viewModelScope.launch {
            repository.insertUserData(userEntity)
        }
    }

    fun updateUserData(userEntity: UserEntity) {
        viewModelScope.launch {
            repository.updateUserData(userEntity)
        }
    }

}