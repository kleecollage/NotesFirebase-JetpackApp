package com.example.notesfirebase.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesfirebase.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    var showAlert by mutableStateOf(false)

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        }else {
                            Log.d("ERROR EN FIREBASE", "Usuario o contraseña incorrectos")
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.d("ERROR EN JETPACK", "Error: ${e.localizedMessage}")
            }
        }
    }

    fun createUser(email: String, password: String, username: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUser(username)
                            onSuccess()
                        }else {
                            Log.d("ERROR EN FIREBASE", "Error al crear usuario")
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.d("ERROR EN JETPACK", "Error: ${e.localizedMessage}")
            }
        }
    }

    private fun saveUser(username: String) {
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch { Dispatchers.IO }
        val user = UserModel(
            userId = id.toString(),
            email = email.toString(),
            username = username
        )

        FirebaseFirestore.getInstance().collection("Users")
            // .document(email)  // Asi se crea el documento en lugar de guardar el modelo en un id generado automaticamente
            // .set(user)
            .add(user)
            .addOnSuccessListener {
                Log.d("GUARDADO EXITOSO", "Se ha guardado el usuario correctamente en firestore")
            }.addOnFailureListener {
                Log.d("ERROR AL GUARDAR USUARIO", "Error al guardar en firestore")
            }
    }

    fun closeAlert() {
        showAlert = false
    }
}