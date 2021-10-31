package com.example.testtask

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtask.core.Resource
import com.koushikdutta.ion.Ion


class Helper(private val context: Context) {


    private var mutableList: MutableLiveData<Resource<MutableList<String>>> = MutableLiveData()
    val list: LiveData<Resource<MutableList<String>>> get() = mutableList

    fun getDataFromUrl(){
        Ion.with(context)
            .load("https://dev-tasks.alef.im/task-m-001/list.php")
            .asString()
            .setCallback { _, result ->  val doc=result!!
                var text= doc.substringAfter('[')
                text=text.substringBefore(']')
                mutableList.value= Resource.success(text.split(",").toMutableList())
            }
    }
}