import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.generativeModel
import com.google.firebase.ai.model.generative.Content
import com.google.firebase.ai.model.generative.part.text
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GeminiHelper {
    // Default uses Gemini Developer API (free tier)
    private val model = FirebaseAI.generativeModel("gemini-1.5-flash")
    // Or try newer models like "gemini-2.0-flash" or "gemini-3-flash-preview" if available

    suspend fun generateResponse(prompt: String): String = withContext(Dispatchers.IO) {
        try {
            val content = Content { text(prompt) }
            val response = model.generateContent(content)
            response.text ?: "No response from Gemini"
        } catch (e: Exception) {
            "Error: ${e.localizedMessage}"
        }
    }
}

