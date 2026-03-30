package com.example.cleanarchitecturecicdjetpackcompose.Dominio

data class AFazer(
    val id: Int,
    val tarefa: String,
    val isChecked: Boolean = false
)