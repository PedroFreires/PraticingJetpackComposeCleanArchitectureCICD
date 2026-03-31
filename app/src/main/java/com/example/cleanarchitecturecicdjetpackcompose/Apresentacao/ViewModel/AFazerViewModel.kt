package com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AFazer
import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AdicionarAFazerCasoDeUso

class AFazerViewModel(
    private val adicionarUseCase: AdicionarAFazerCasoDeUso
) : ViewModel() {

    //Lista observavel pelo Compose
    private val _tarefas = mutableStateListOf<AFazer>()
    val tarefas: List<AFazer> = _tarefas

    fun adicionarTarefa(nome: String) {
        val sucesso = adicionarUseCase.executar(nome)

        if (sucesso) {
            //O certo seria buscar no Repositorio
            //Por hora, vamos buscar local
            _tarefas.add(AFazer(id = (0..1000).random(), tarefa = nome))
        }
    }
    fun alternarTarefa(tarefaAlvo: AFazer) {
        val index = _tarefas.indexOf(tarefaAlvo)
        if (index != -1) {
            // No Compose, para a lista atualizar a UI,
            // precisamos trocar o objeto por uma cópia alterada
            _tarefas[index] = tarefaAlvo.copy(isChecked = !tarefaAlvo.isChecked)
        }
    }
}