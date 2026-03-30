package com.example.cleanarchitecturecicdjetpackcompose.Dominio

interface AFazerRepository {
    fun salvar(tarefa: AFazer): Boolean
    fun listar(): List<AFazer>
}