package com.example.cleanarchitecturecicdjetpackcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ui.screens.TelaDeTarefas
import com.example.cleanarchitecturecicdjetpackcompose.ui.theme.CleanArchitectureCICDJetpackComposeTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TelaInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textField_deveTerHintCorreto() {
        composeTestRule.setContent {
            CleanArchitectureCICDJetpackComposeTheme {
                TelaDeTarefas()
            }
        }
        // 1. Verifica o hint "Insira uma tarefa"
        composeTestRule.onNodeWithText("Insira uma tarefa").assertIsDisplayed()
    }

    @Test
    fun button_deveAddItensLimparCampoText() {

        composeTestRule.setContent {
            CleanArchitectureCICDJetpackComposeTheme {
                TelaDeTarefas()
            }
        }
        val inputText = "Comprar leite"

        // Encontra o TextField e digita
        composeTestRule.onNodeWithText("Insira uma tarefa").performTextInput(inputText)

        // Encontra o botão e clica
        composeTestRule.onNodeWithText("Adicionar").performClick()

        // 2. Verifica se a tarefa aparece na lista
        composeTestRule.onNodeWithText(inputText).assertIsDisplayed()

        // 3. Verifica se o TextField está vazio novamente
        composeTestRule.onNodeWithText("Insira uma tarefa").assertIsDisplayed()
    }
}