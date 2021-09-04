package com.example.tarjetamatrimonio

import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity() {

    var point = Point()
    var yPress = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        back.setOnClickListener { startActivity(Intent(this@GameActivity, MainActivity::class.java)) }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("yPress: $yPress")
        when (event!!.action) {

            MotionEvent.ACTION_DOWN ->{
                yPress = event.getRawY()
            }
            MotionEvent.ACTION_UP ->{
                when {
                    event.getRawY()<yPress -> player.y -= 100
                    event.getRawY()>yPress -> player.y += 100
                }
            }
        }
        return super.onTouchEvent(event)
    }
}