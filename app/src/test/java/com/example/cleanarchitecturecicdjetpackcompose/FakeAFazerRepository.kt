package com.example.cleanarchitecturecicdjetpackcompose

import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AFazer
import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AFazerRepository

class FakeAFazerRepository : AFazerRepository {
    private val tarefas = mutableListOf<AFazer>()

    override fun salvar(tarefa: AFazer): Boolean {
        tarefas.add(tarefa)
        return true
    }

    override fun listar(): List<AFazer> = tarefas
}