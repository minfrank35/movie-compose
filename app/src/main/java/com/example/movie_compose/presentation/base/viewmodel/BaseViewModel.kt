package com.example.movie_compose.presentation.base.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

  protected val key: ViewModelKey = ViewModelKey(this::class.java.name)

  protected fun <T> BaseViewModel.viewModelStateFlow(value: T): ViewModelStateFlow<T> {
    return ViewModelStateFlow(key = key, value = value)
  }
}
