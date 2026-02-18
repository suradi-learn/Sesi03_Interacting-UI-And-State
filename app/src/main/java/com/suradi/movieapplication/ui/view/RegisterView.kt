package com.suradi.movieapplication.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue          // ✅ penting
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue          // ✅ penting
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegisterView() {

    var name by rememberSaveable { mutableStateOf(value = "") }
    var email by rememberSaveable { mutableStateOf(value = "")}
    var password by rememberSaveable { mutableStateOf(value = "") }
    var passwordVisible by rememberSaveable { mutableStateOf(value = false)}
    var showDialog by rememberSaveable { mutableStateOf(value = false)}

    val isNameValid = isValidName(name)
    val isEmailValid = isValidEmail(email)
    val isPasswordValid = isValidPassword(password)
    val formValid = isNameValid && isEmailValid && isPasswordValid

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text( text = "Name" )},
            isError = name.isNotEmpty() && !isNameValid,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text( text = "Email" )},
            isError = email.isNotEmpty() && !isEmailValid,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text( text = "Password" )},
            visualTransformation =
                if(passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            },
            isError = false,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { showDialog = true },
            enabled = formValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text( text = "Submit")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text( text = "Submitted Data") },
            text = { Text(text = "Email: $email\nPassword: $password")},
            confirmButton = {
                TextButton(onClick = { showDialog = false }) { Text (text = "OK") }
            }

        )
    }
}

private fun isValidName(name: String): Boolean {
    return name.isNotBlank()
}

private fun isValidEmail(email: String): Boolean {
    return email.trim().matches(Regex(pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
}

private fun isValidPassword(password: String): Boolean {
    return password.length >= 6
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun RegisterPreview() {
    RegisterView()
}