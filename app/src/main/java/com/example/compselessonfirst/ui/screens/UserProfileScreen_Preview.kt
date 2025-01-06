package com.example.compselessonfirst.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compselessonfirst.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun UserProfileScreen_Preview() {

    var name by rememberSaveable { mutableStateOf("Danil") }
    var info by rememberSaveable { mutableStateOf("Android \nexample@gmail.com") }
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "User Profile",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    navigationIconContentColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Avatar(imageRes = R.drawable.ic_launcher_background)

                Spacer(modifier = Modifier.height(16.dp))

                UserInfo(name = name, info = info)

                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    text = "Edit Profile",
                    onClick = { isDialogOpen = true },
                )
            }

            if (isDialogOpen) {
                EditProfileDialog(
                    currentName = name,
                    currentInfo = info,
                    onCancel = { isDialogOpen = false },
                    onSave = { newName, newInfo ->
                        name = newName
                        info = newInfo
                        isDialogOpen = false
                    }
                )
            }
        }
    )
}

@Composable
fun Avatar(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Avatar",
        modifier = Modifier
            .size(164.dp)
            .clip(CircleShape),
    )
}

@Composable
fun UserInfo(name: String, info: String) {
    Text(
        text = name,
        fontSize = 42.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = info,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun EditProfileDialog(
    currentName: String,
    currentInfo: String,
    onCancel: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var newName by rememberSaveable { mutableStateOf(currentName) }
    var newInfo by rememberSaveable { mutableStateOf(currentInfo) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Edit Profile") },
        text = {
            Column {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newInfo,
                    onValueChange = { newInfo = it },
                    label = { Text("Info") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = { onSave(newName, newInfo) }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}