package com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AdicionarAFazerCasoDeUso

class AFazerViewModelFactory(
    private val useCase: AdicionarAFazerCasoDeUso
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AFazerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AFazerViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}