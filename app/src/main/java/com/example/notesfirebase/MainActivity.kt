package com.example.notesfirebase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesfirebase.navigation.NavManager
import com.example.notesfirebase.ui.theme.NotesFirebaseTheme
import com.example.notesfirebase.viewModels.LoginViewModel
import com.example.notesfirebase.viewModels.NotesViewModel
import com.example.notesfirebase.views.login.TabsView

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        val loginVM: LoginViewModel by viewModels()
        val notesVM: NotesViewModel by viewModels()

        setContent {
            NotesFirebaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavManager(loginVM, notesVM )
                }
            }
        }
    }
}