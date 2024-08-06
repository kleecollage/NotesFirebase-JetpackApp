package com.example.notesfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesfirebase.viewModels.LoginViewModel
import com.example.notesfirebase.viewModels.NotesViewModel
import com.example.notesfirebase.views.login.BlankView
import com.example.notesfirebase.views.login.TabsView
import com.example.notesfirebase.views.notes.AddNoteView
import com.example.notesfirebase.views.notes.EditNoteView
import com.example.notesfirebase.views.notes.HomeView

@Composable
fun NavManager(loginVM: LoginViewModel, notesVM: NotesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Blank") {
        composable("Blank") {
            BlankView(navController)
        }
        composable("Login") {
            TabsView(navController, loginVM)
        }
        composable("Home") {
            HomeView(navController, notesVM)
        }
        composable("AddNoteView") {
            AddNoteView(navController, notesVM)
        }
        composable("EditNoteView/{idDoc}", arguments = listOf(
            navArgument("idDoc") { type = NavType.StringType }
        )) {
            val idDoc = it.arguments?.getString("idDoc") ?: ""
            EditNoteView(navController, notesVM, idDoc)
        }
    }
}