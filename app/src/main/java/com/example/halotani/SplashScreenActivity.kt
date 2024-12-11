package com.example.halotani

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.halotani.ui.theme.HaloTaniTheme
import com.example.halotani.auth.LoginActivity


class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HaloTaniTheme {
                SplashScreen {
                    // Intent untuk berpindah ke MainActivity setelah tombol ditekan
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish() // Tutup SplashScreenActivity agar tidak kembali ke sini
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo di tengah layar
        Image(
            painter = painterResource(id = R.drawable.hahahah__1_), // ganti dengan nama logo Anda
            contentDescription = "Logo Halo Tani",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Teks Sambutan
        Text(
            text = "Selamat Datang di Halo Tani",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Mulai
        Button(onClick = onStartClick) {
            Text(text = "Mulai")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    HaloTaniTheme {
        SplashScreen(onStartClick = {})
    }
}
