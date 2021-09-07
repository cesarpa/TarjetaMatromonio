package com.example.tarjetamatrimonio

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tarjetamatrimonio.controller.GameData
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity(), Runnable {


    var thread = Thread(this)
    var playing = true;
    var data: GameData = GameData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        back.setOnClickListener {
            playing = false
            startActivity(
                Intent(
                    this@GameActivity,
                    MainActivity::class.java
                )
            )
        }
        thread.start()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                data.yPress = event.getRawY()
            }
            MotionEvent.ACTION_UP -> {
                when {
                    event.getRawY() < data.yPress -> player.y -= 100
                    event.getRawY() > data.yPress -> player.y += 100
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun run() {
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.coin)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imageView.getLayoutParams().height = 100
        imageView.x = 1200f
        val rnds = (180..780).random()
        imageView.y = rnds.toFloat()
        data.coinList.add(imageView)
        gameLayout.addView(data.coinList.get(0))
        while (playing) {
            draw()
            sleep()
        }
    }

    private fun draw() {
        data.coinList.forEach {
            it.x -= 10
            if (it.x < -800) {
                it.x = 1200f
                val rnds = (180..780).random()
                it.y = rnds.toFloat()
            }
        }
        println("creating coins")
    }
    private fun sleep() {
        Thread.sleep(17)
    }

}