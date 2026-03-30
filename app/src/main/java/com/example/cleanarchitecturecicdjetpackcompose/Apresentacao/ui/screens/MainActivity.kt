package com.example.cleanarchitecturecicdjetpackcompose.Apresentacao.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cleanarchitecturecicdjetpackcompose.ui.theme.CleanArchitectureCICDJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureCICDJetpackComposeTheme {
//                MyCustomButton()
            }
        }
    }
}