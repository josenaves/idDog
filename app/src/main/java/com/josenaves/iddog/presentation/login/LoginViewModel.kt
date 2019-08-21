package com.josenaves.iddog.presentation.login

import androidx.lifecycle.ViewModel
import com.josenaves.iddog.data.ConfigurationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class LoginViewModel(private val configurationRepository: ConfigurationRepository) : ViewModel() {

    companion object {
        const val TAG = "SectionViewModel"
    }

    private val viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}