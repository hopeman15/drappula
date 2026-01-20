package com.hellocuriosity.drappula.ui.common

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <VM : ViewModel> createViewModelFactory(viewModelProvider: () -> VM): () -> ViewModelProvider.Factory =
    {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModelProvider() as T
        }
    }

inline fun <reified VM : ViewModel> ComponentActivity.viewModelBuilder(noinline viewModelProvider: () -> VM): Lazy<VM> =
    viewModels(factoryProducer = createViewModelFactory(viewModelProvider))
