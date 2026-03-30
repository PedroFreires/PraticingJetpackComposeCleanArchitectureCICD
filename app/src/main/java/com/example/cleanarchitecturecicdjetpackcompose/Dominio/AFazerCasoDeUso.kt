package com.example.cleanarchitecturecicdjetpackcompose.Dominio

class AdicionarAFazerCasoDeUso(private val repository: AFazerRepository) {
    fun executar(tarefa: String): Boolean {
        if (tarefa.isBlank()) return false
        // repository.salvar(tarefa)

        val novaTarefa = AFazer(
            id = (0..10000).random(),
            tarefa = tarefa,
            isChecked = false
        )

        return repository.salvar(novaTarefa)
    }
}