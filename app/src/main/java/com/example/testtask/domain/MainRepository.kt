package com.example.testtask.domain

import com.example.testtask.data.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val api:ApiInterface) {

    fun getImages(): Flow<Result<List<String>>> = flow{
        val response=api.getImage()
        if(response.isSuccessful){
            emit(Result.success(response.body()!!))
        }
        else{
            emit(Result.failure<List<String>>(Throwable("Произошла ошибка")))
        }
    }.flowOn(Dispatchers.IO)
}