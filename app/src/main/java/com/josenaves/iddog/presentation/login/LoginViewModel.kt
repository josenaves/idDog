package com.josenaves.iddog.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josenaves.iddog.common.architecture.Event
import com.josenaves.iddog.common.architecture.UiState
import com.josenaves.iddog.data.AuthRepository
import com.josenaves.iddog.data.ConfigurationRepository
import com.josenaves.iddog.data.TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val configurationRepository: ConfigurationRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    companion object {
        const val TAG = "SectionViewModel"
    }

    private val viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val uiState = MutableLiveData<Event<UiState<String>>>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun login(email: String) = ioScope.launch {
        uiState.postValue(Event(UiState.Loading()))
        try {
            val token = authRepository.auth(email)

            // save token
            configurationRepository.insert(TOKEN, token)

            // all done - move to next screen
            uiState.postValue(Event(UiState.Success(token)))

        } catch (e: Throwable) {
            uiState.postValue(Event(UiState.Error(e)))
        }
    }

}