# GeminiChatApp - Android Chat App with Gemini AI
A simple, modern Android chat application built with Kotlin and Jetpack Compose that lets you talk to Google's Gemini AI directly on your phone.

## Features
Clean Material 3 chat interface
Real-time conversation with Gemini
Auto-scroll to latest message
Loading indicator while Gemini is thinking
Built with modern Android tools (Compose, Coroutines, Firebase)

## Requirements

-Android Studio (Koala or later recommended)
-Android device or emulator (API 23+)
-Internet connection (Gemini is cloud-based)
-Due to some reason, user need to self create your own api key at https://aistudio.google.com/api-keys and change it at app/src/google-services.json

### Setup Instructions
1. Clone the Repository
'''Bash
git clone https://github.com/yourusername/GeminiChatApp.git
cd GeminiChatApp

2. Connect to Firebase
Go to https://console.firebase.google.com
Create a new project (or use an existing one)
Add an Android app to the project:
Package name: com.example.geminichatapp
Download google-services.json and place it in the app/ folder

3. Enable Gemini in Firebase
In your Firebase project console, go to AI Logic (under Build section)
Click Get started
Choose Gemini Developer API (recommended – free tier available)
Follow the setup wizard

4. Open and Run in Android Studio
Open the project in Android Studio
Wait for Gradle sync to complete
Connect your phone (USB or wireless debugging) or start an emulator
Click Run (green play button)

### Tech Stack
Language: Kotlin
UI: Jetpack Compose + Material 3
AI: Firebase AI Logic SDK (Gemini 2.5+ models)
Architecture: Simple MVVM-like with Compose state
Coroutines for async network calls

### Current Model
Uses gemini-2.5-flash – fast, cost-effective, and up-to-date (January 2026).
