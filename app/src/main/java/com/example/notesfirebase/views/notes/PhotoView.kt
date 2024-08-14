package com.example.notesfirebase.views.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.example.notesfirebase.viewModels.NotesViewModel

@Composable
fun PhotoView(notesVM: NotesViewModel, idDoc: String) {
    LaunchedEffect(Unit) {
        notesVM.getNoteById(idDoc)
    }

    Column {
        val state = notesVM.state
        if (state.imagePath.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(state.imagePath),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
    }
}





