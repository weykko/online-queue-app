package weyko.online_queue_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import weyko.online_queue_app.ui.theme.OnlinequeueappTheme
import androidx.core.net.toUri

class SuccessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val userName = intent.getStringExtra("name") ?: ""
        setContent {
            OnlinequeueappTheme {
                SuccessScreen(userName = userName)
            }
        }
    }
}

@Composable
fun SuccessScreen(userName: String) {
    val context = LocalContext.current
    val phoneNumber = "88005553535"
    val phoneDisplay = "8 800 555 35 35"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(top = 80.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = RoundedCornerShape(24.dp),
                color = Color(0xFF4CAF50)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "✓",
                        color = Color.White,
                        fontSize = 52.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = buildAnnotatedString {
                    append("Отлично, ")
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(userName)
                    }
                    append("! Мы\nвам перезвоним.")
                },
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, "tel:$phoneNumber".toUri())
                    context.startActivity(intent)
                }
            ) {
                Text(
                    text = phoneDisplay,
                    color = Color(0xFF1565C0),
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                    )
                )
            }
        }
    }
}

