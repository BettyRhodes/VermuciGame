package com.example.vermuci

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import java.lang.Thread.sleep

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_main)

       // window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
       //     WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val thread = Thread{
            try {
                sleep(3000)
                startActivity(Intent(applicationContext, StartActivity::class.java))
                finish()
            }catch (e: InterruptedException){
                e.printStackTrace()
            }
        }

        thread.start()
    }
}
