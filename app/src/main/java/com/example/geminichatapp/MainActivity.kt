package com.example.geminichatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class Message(val text: String, val isUser: Boolean)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ChatScreen()
            }
        }
    }
}

@Composable
fun ChatScreen() {
    var input by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Message>() }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f).padding(16.dp)
        ) {
            items(messages) { msg ->
                Text(
                    text = msg.text,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    color = if (msg.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
            if (isLoading) {
                item { Text("Gemini is thinking...", Modifier.padding(8.dp)) }
            }
        }

        Row(Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                label = { Text("Ask Gemini...") }
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        messages.add(Message(input, true))
                        val query = input
                        input = ""
                        isLoading = true

                        scope.launch {
                            var fullResponse = ""
                            GeminiHelper.generateResponseStream(query).collect { chunk ->
                                fullResponse += chunk
                                // Replace or update the last bot message
                                if (messages.lastOrNull()?.isUser == false) {
                                    messages[messages.lastIndex] = Message(fullResponse, false)
                                } else {
                                    messages.add(Message(fullResponse, false))
                                }
                                listState.animateScrollToItem(messages.size - 1)
                            }
                            isLoading = false
                        }
                    }
                },
                enabled = !isLoading
            ) {
                Text("Send")
            }
        }
    }

    // Auto-scroll to bottom when new message arrives
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }
}