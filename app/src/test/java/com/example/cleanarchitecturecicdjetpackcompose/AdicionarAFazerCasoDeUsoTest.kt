package com.example.cleanarchitecturecicdjetpackcompose

import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AdicionarAFazerCasoDeUso
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse

import org.junit.Assert.assertTrue
import org.junit.Test

// Casos de uso do Aplicativo

class AdicionarAFazerCasoDeUsoTest {

    @Test
    fun nomeDaFuncaoRetornaTrueMesmoSendoFalse() {
        val resultadoEsperado = 1
        val resultadoReal = 2

        assertEquals(resultadoEsperado, resultadoReal)
    }

    @Test
    fun deveAdicionarTarefaComSucesso() {
        val repository = FakeAFazerRepository()
        val useCase = AdicionarAFazerCasoDeUso(repository)

        val resultado = useCase.executar("Estudar Kotlin")

        assertTrue(resultado)
        assertEquals(1, repository.listar().size)
    }

    @Test
    fun naoDeveAdicionarTarefaSeONomeForVazio() {
        val repository = FakeAFazerRepository()
        val useCase = AdicionarAFazerCasoDeUso(repository)

        // Tenta adicionar uma string vazia
        val resultado = useCase.executar("")

        // O resultado deve ser falso
        assertFalse(resultado)
        // A lista deve continuar com 0 itens
        assertEquals(0, repository.listar().size)
    }
}