package com.example.tarjetamatrimonio.views

import android.content.Context
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), Runnable {

    init {

    }

    lateinit var thread: Thread

    override fun run() {
        TODO("Not yet implemented")
    }

    fun pause() {
        TODO("Not yet implemented")
    }

    fun resume() {
        thread = Thread(this)
        thread.start()
    }
}