package com.example.homework11

import android.os.Handler
import android.os.Looper
import kotlin.concurrent.thread

class AuthService: IAuthService {
    private val handler = Handler(Looper.getMainLooper())

    override fun auth(searchQuery: String, onSearchedIn: () -> Unit) {
        thread {
            Thread.sleep(2000L)
            handler.post {
                onSearchedIn.invoke()
            }
        }
    }
}