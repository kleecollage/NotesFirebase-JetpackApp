package com.example.notesfirebase.model

data class NotesState(
    val idDoc: String = "",
    val emailUser: String = "",
    val title: String = "",
    val note: String = "",
    val date: String = "",
    val imagePath: String = ""
)