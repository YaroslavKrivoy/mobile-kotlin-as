package com.example.kryvyi.android_project.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kryvyi.android_project.R

private const val NETWORK_COVER_URL = "https://www.gutenberg.org/cache/epub/1342/pg1342.cover.medium.jpg"

@Composable
fun BookImageDemo(
    modifier: Modifier = Modifier
) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri
        }
    }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("Локальний resource")

        Image(
            painter = painterResource(R.drawable.book_cover_local),
            contentDescription = "Локальна обкладинка",
            modifier = Modifier.width(180.dp).height(260.dp),
            contentScale = ContentScale.Crop
        )

        Button(
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
        ) {
            Text("Обрати зображення")
        }

        selectedImageUri?.let { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Обране зображення",
                modifier = Modifier
                    .width(180.dp)
                    .height(260.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text("Мережеве зображення")

        AsyncImage(
            model = NETWORK_COVER_URL,
            contentDescription = "Pride and Prejudice",
            placeholder = painterResource(R.drawable.book_loading),
            error = painterResource(R.drawable.book_error),
            modifier = Modifier
                .width(180.dp)
                .height(260.dp),
            contentScale = ContentScale.Crop
        )

    }
}

