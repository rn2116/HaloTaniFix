package com.example.halotani.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.halotani.MainActivity
import com.example.halotani.ui.theme.HaloTaniTheme
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HaloTaniTheme {
                SignUpScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen() {
    // State variables for user input
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // Get the context for showing Toast and navigating to another screen
    val context = LocalContext.current

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Username Input Field
                OutlinedTextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email Input Field
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Input Field
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Sign Up Button
                Button(
                    onClick = {
                        if (username.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()) {
                            registerUser(username.value, email.value, password.value, context)
                        } else {
                            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Sign Up")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}

fun registerUser(username: String, email: String, password: String, context: android.content.Context) {
    // Reference to Firebase Realtime Database
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("users")

    // Create user data structure
    val userData = mapOf(
        "username" to username,
        "email" to email,
        "password" to password
    )

    // Store user data in Firebase, replacing `.` in email with `,` for valid keys
    usersRef.child(email.replace(".", ",")).setValue(userData)
        .addOnSuccessListener {
            // Success
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            // Navigate to MainActivity
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
        .addOnFailureListener {
            // Failure
            Toast.makeText(context, "Registration failed: ${it.message}", Toast.LENGTH_SHORT).show()
        }
}
