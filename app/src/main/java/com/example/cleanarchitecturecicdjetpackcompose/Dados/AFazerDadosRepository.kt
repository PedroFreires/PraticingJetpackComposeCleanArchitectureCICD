package com.example.cleanarchitecturecicdjetpackcompose.Dados

import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AFazerRepository

class AFazerDadosRepository(private val repository: AFazerRepository) {
    fun executar(tarefa: String): Boolean{
        if (tarefa.isBlank()) return false

        return true
    }
}