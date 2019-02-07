package com.example.vermuci

import android.annotation.SuppressLint
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var playerY: Int = 0
    private var foodX: Int = 0
    private var foodY: Int = 0
    private var diamondX: Int = 0
    private var diamondY: Int = 0
    private var enemy1X: Int = 0
    private var enemy1Y: Int = 0
    private var enemy2X: Int = 0
    private var enemy2Y: Int = 0

    private var playerSpeed = 0
    private var foodSpeed: Int = 0
    private var diamondSpeed: Int = 0
    private var enemy1Speed: Int = 0
    private var enemy2Speed: Int = 0

    private var score: Int = 0

    private var handler = Handler()
    private var timer = Timer()

    private var action_flag = false
    private var start_flag = false
    private var pause_flag = false

    private var playerSize = 0
    private var frameHeight = 0
    private var screenWidth = 0
    private var screenHeight = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wm = windowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)

        pause_button.isEnabled = false
        frame_lb.visibility = View.GONE

        screenWidth = size.x
        screenHeight= size.y

        playerSpeed = Math.round((screenWidth / 60).toDouble()).toInt()
        foodSpeed = Math.round((screenWidth / 60).toDouble()).toInt()
        diamondSpeed = Math.round((screenWidth / 30).toDouble()).toInt()
        enemy1Speed = Math.round((screenWidth / 44).toDouble()).toInt()
        enemy2Speed = Math.round((screenWidth / 39).toDouble()).toInt()

        food.x = -80f
        food.y = -80f
        diamond.x = -80f
        diamond.y = -80f
        enemy1.x = -80f
        enemy1.y = -80f
        enemy2.x = -80f
        enemy2.y = -80f

        score_text.text = "Score: 0"
    }

    @SuppressLint("SetTextI18n")
    fun position(){
        if(action_flag){
            playerY -= playerSpeed
            player.setImageResource(R.drawable.player1)
        }else{
            playerY += playerSpeed
            player.setImageResource(R.drawable.player2)
        }

        if(playerY < 0)
            playerY = 0

        if(playerY > frameHeight - playerSize)
            playerY = frameHeight - playerSize

        player.y = playerY.toFloat()
        score_text.text = "Score: $score"
    }

    override fun onTouchEvent(me: MotionEvent): Boolean{
        if(!start_flag){

            start_flag = true
            frameHeight = frame.height

            playerY = player.y.toInt()

            playerSize = player.height

            start_text.visibility = View.GONE
            pause_button.isEnabled = true

            timer.schedule(object : TimerTask() {
                override fun run() {
                    handler.post {
                        position()
                    }
                }

        }, 0, 20)
        }else{
            if(me.action == MotionEvent.ACTION_DOWN){
                action_flag = true
            }else if(me.action == MotionEvent.ACTION_UP){
                action_flag = false
            }
        }

        return true
    }
}
