package com.example.rick_and_morty_api

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.rick_and_morty_api.utils.Resource
import com.example.rick_and_morty_api.utils.showToast

abstract class BaseActivity : AppCompatActivity() {

    fun <T> LiveData<Resource<T>>.observeResource(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit)? = null
    ) {
        observe(this@BaseActivity) {resource->
            if (state != null) {
                state(resource)
            }
            when (resource) {
                is Resource.Error -> {
                    resource.message?.let {
                        this@BaseActivity.showToast(it)
                    }
                }

                is Resource.Loading -> {}
                is Resource.SuccLoading -> {
                    if (resource.data != null) {
                        success(resource.data)
                    }
                }
            }
        }
    }

}