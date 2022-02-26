package com.example.testtask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.MainRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val mainRepository: MainRepository):ViewModel() {

    private var mutableSuccess: MutableLiveData<List<String>> = MutableLiveData()
    val success: LiveData<List<String>> = mutableSuccess
    private var mError: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = mError

    fun getImages(){
        mainRepository.getImages().onEach {
            it.onSuccess { res->
                mutableSuccess.value=res
            }
            it.onFailure { th->
                mError.value=th.message
            }
        }.catch() {
            mError.value = "catch error in viewModel"
        }.launchIn(viewModelScope)
    }
}