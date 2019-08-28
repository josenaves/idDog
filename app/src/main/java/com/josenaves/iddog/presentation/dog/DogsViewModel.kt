package com.josenaves.iddog.presentation.dog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josenaves.iddog.common.architecture.Event
import com.josenaves.iddog.common.architecture.UiState
import com.josenaves.iddog.data.DogRepository
import com.josenaves.iddog.model.Dog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DogsViewModel(
    private val dogRepository: DogRepository
) : ViewModel() {

    companion object {
        const val TAG = "DogsViewModel"
    }

    private val viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val uiState = MutableLiveData<Event<UiState<List<Dog>>>>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getDogs() = ioScope.launch {
        uiState.postValue(Event(UiState.Loading()))

        try {
            val dogs = dogRepository.getAll()
            uiState.postValue(Event(UiState.Success(dogs)))
        } catch (t: Throwable) {
            uiState.postValue(Event(UiState.Error(t)))
        }
    }
}