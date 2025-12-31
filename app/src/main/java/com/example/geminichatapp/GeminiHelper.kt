package com.example.geminichatapp

import com.google.firebase.Firebase
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content  // Correct package for content { }
import com.google.firebase.ai.type.asTextOrNull       // Correct for text()
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GeminiHelper {
    // Recommended: Gemini Developer API (free tier)
    private val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-2.5-flash")  // Or "gemini-2.5-flash" if you prefer newer

    suspend fun generateResponse(prompt: String): String = withContext(Dispatchers.IO) {
        try {
            val response = model.generateContent(content { text(prompt) })
            response.text ?: "No response from Gemini"
        } catch (e: Exception) {
            "Error: ${e.localizedMessage}"
        }
    }
}
