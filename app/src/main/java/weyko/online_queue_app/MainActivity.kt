package weyko.online_queue_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import weyko.online_queue_app.ui.theme.OnlinequeueappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlinequeueappTheme {
                QueueFormScreen()
            }
        }
    }
}

@Composable
fun QueueFormScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var question by remember { mutableStateOf(TextFieldValue("")) }
    var nameError by remember { mutableStateOf(false) }

    val errorColor = Color(0xFFD32F2F)
    val buttonColor = Color(0xFF9C85C0)
    val fieldBackground = Color(0xFFEEEEEE)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 80.dp),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    if (it.text.isNotBlank()) nameError = false
                },
                placeholder = { Text("Введите имя", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                isError = nameError,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = fieldBackground,
                    focusedContainerColor = fieldBackground,
                    errorContainerColor = fieldBackground,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF9C85C0),
                    errorBorderColor = errorColor
                ),
                singleLine = true
            )
            if (nameError) {
                Text(
                    text = "Обязательно для заполнения",
                    color = errorColor,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = question,
                onValueChange = { question = it },
                placeholder = { Text("Вопрос", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = fieldBackground,
                    focusedContainerColor = fieldBackground,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF9C85C0)
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.text.isBlank()) {
                        nameError = true
                    } else {
                        val intent = Intent(context, SuccessActivity::class.java).apply {
                            putExtra("name", name.text.trim())
                        }
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text("Отправить", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}