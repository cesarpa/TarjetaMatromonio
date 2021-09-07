package com.example.tarjetamatrimonio

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity(), Runnable {

    var yPress = 0f
    var thread = Thread(this)
    var playing = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        thread.start()
        back.setOnClickListener {
            playing = false
            startActivity(
                Intent(
                    this@GameActivity,
                    MainActivity::class.java
                )
            )
        }

        val textView = TextView(this)
        textView.textSize = 40f
        textView.text = "text"

        gameLayout.addView(textView)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                yPress = event.getRawY()
            }
            MotionEvent.ACTION_UP -> {
                when {
                    event.getRawY() < yPress -> player.y -= 100
                    event.getRawY() > yPress -> player.y += 100
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun run() {
        while (playing) {
            draw()
            sleep()
        }
    }

    private fun draw() {
        println("running")
    }

    private fun sleep() {
        Thread.sleep(17)
    }

}