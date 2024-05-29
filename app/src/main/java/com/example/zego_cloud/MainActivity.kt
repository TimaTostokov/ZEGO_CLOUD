package com.example.zego_cloud

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zego_cloud.ui.theme.ZEGO_CLOUDTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZEGO_CLOUDTheme {
                MockLogin()
            }
        }
    }
}

@Composable
fun MockLogin() {

    var roomID by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "audio box", fontSize = 32.sp, fontFamily = FontFamily.Monospace)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = roomID, onValueChange = {
            roomID = it
        }, label = { Text(text = "Room ID") })

        OutlinedTextField(value = username, onValueChange = {
            username = it
        }, label = { Text(text = "username") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            roomID = generateRoomID()
            val intent = Intent(context, LiveAudioRoomActivity::class.java)
            intent.putExtra("userId", username)
            intent.putExtra("roomId", roomID)
            intent.putExtra("isHost", true)
            context.startActivity(intent)

        }) {
            Text(text = "Start AudioBox")
        }

        Button(onClick = {
            val intent = Intent(context, LiveAudioRoomActivity::class.java)
            intent.putExtra("userId", username)
            intent.putExtra("roomId", roomID)
            intent.putExtra("isHost", false)
            context.startActivity(intent)
        }) {
            Text(text = "Join AudioBox")
        }
    }
}

fun generateRoomID(): String {
    val id = StringBuilder()
    while (id.length < 5) {
        val random = Random().nextInt(10)
        id.append(random)
    }
    return id.toString()
}