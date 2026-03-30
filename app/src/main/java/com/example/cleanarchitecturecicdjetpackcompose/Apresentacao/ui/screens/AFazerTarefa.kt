package com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecturecicdjetpackcompose.ui.theme.CleanArchitectureCICDJetpackComposeTheme

class AFazerTarefa : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureCICDJetpackComposeTheme {
                TelaComToolbar()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaComToolbar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFC01119),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TelaDeTarefas()
        }
    }
}

@Composable
fun TelaDeTarefas() {
    // Estado para o texto do TextField
    var tarefaTexto by remember { mutableStateOf("") }
    // Estado para a lista de tarefas (simulando o que vai pro LazyColumn)
    var listaDeTarefas by remember { mutableStateOf(listOf<Pair<String, Boolean>>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 1. O TextField com o hint (label) que o teste procura
            OutlinedTextField(
                value = tarefaTexto,
                onValueChange = { tarefaTexto = it },
                label = { Text("Insira uma tarefa") },
                modifier = Modifier.weight(1f)
            )

            // 2. O Botão "Adicionar"
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC01119),
                    contentColor = Color(0xFFF6F6F6)
                ),
                onClick = {
                    if (tarefaTexto.isNotBlank()) {
                        listaDeTarefas = listaDeTarefas + (tarefaTexto to false)
                        tarefaTexto = "" // Limpa o campo (Requisito 3 do seu teste)
                    }
                },
            ) {
                Text("Adicionar")
            }
        }

        // 3. Exibição das tarefas (Para o Requisito 2 do seu teste)
        listaDeTarefas.forEachIndexed { index, tarefa ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = tarefa.second,
                    onCheckedChange = { isChecked ->
                        //Atualiza o estado do checkbox
                        val novaLista = listaDeTarefas.toMutableList()
                        novaLista[index] = tarefa.first to isChecked
                        listaDeTarefas = novaLista
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFC01119),   // Cor do fundo quando marcado
                        checkmarkColor = Color.White
                    )
                )
                Text(text = tarefa.first)
            }
        }
    }
}
@Preview
@Composable
fun TelaDeTarefasPreview() {
    CleanArchitectureCICDJetpackComposeTheme() {

        TelaDeTarefas()
    }
}
