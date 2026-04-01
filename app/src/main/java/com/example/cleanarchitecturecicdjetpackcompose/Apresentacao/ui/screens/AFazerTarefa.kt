package com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ViewModel.AFazerViewModel
import com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ViewModel.AFazerViewModelFactory
import com.example.cleanarchitecturecicdjetpackcompose.Dados.FakeAFazerDadosRepository
import com.example.cleanarchitecturecicdjetpackcompose.Dominio.AdicionarAFazerCasoDeUso
import com.example.cleanarchitecturecicdjetpackcompose.ui.theme.CleanArchitectureCICDJetpackComposeTheme
import android.view.View

class AFazerTarefa : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val repository = FakeAFazerDadosRepository() // O ideal é trocar por um real
        val useCase = AdicionarAFazerCasoDeUso(repository)
        val factory = AFazerViewModelFactory(useCase)

        val viewModel = ViewModelProvider(this, factory)[AFazerViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            CleanArchitectureCICDJetpackComposeTheme {
                TelaComToolbar(viewModel)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaComToolbar(viewModel: AFazerViewModel) {
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
            TelaDeTarefas(viewModel)
        }
    }
}

@Composable
fun TelaDeTarefas(viewModel: AFazerViewModel) {
    // Estado para o texto do TextField
    var tarefaTexto by remember { mutableStateOf("") }
    // Estado para a lista de tarefas
    var listaDeTarefas = viewModel.tarefas

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
                        viewModel.adicionarTarefa(tarefaTexto)
                        tarefaTexto = "" // Limpa o campo (Requisito 3 do seu teste)
                    }
                },
            ) {
                Text("Adicionar")
            }
        }

        // 3. Exibição das tarefas (Para o Requisito 2 do seu teste)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items(listaDeTarefas) { tarefa ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = tarefa.isChecked,
                        onCheckedChange = { isChecked ->
                            //Atualiza o estado do checkbox
                            viewModel.alternarTarefa(tarefa)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFC01119),   // Cor do fundo quando marcado
                            checkmarkColor = Color.White
                        )
                    )
                    Text(text = tarefa.tarefa)
                }
            }
        }
    }
}
@Preview
@Composable
fun TelaDeTarefasPreview() {
    CleanArchitectureCICDJetpackComposeTheme() {
        val repository = FakeAFazerDadosRepository() // O ideal é trocar por um real
        val useCase = AdicionarAFazerCasoDeUso(repository)
        val viewModel = AFazerViewModel(useCase)

        TelaDeTarefas(viewModel)
    }
}
