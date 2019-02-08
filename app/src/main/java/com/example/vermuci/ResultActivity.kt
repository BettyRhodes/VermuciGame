package com.example.vermuci

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private var highScore = 0

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra(EXTRA_SCORE,0)
        score_result.text = "" + score

        val preferenceScore = getSharedPreferences("HIGHSCORE", Context.MODE_PRIVATE)
        highScore = preferenceScore.getInt("HIGHSCORE", 0)

        if(score > highScore){
            high_score.text = "High Score: $score"

            val editor = preferenceScore.edit()
            editor.putInt("HIGHSCORE", score)
            editor.apply()
        }else {
            high_score.text = "High Score: $highScore"
        }

        val preferencesGames = getSharedPreferences("GAMES", Context.MODE_PRIVATE)
        var games = preferencesGames.getInt("GAMES", 0)

        game_played.text = "Games played: " + (games + 1)

        val editor = preferencesGames.edit()
        editor.putInt("GAMES", (games + 1))
        editor.apply()
    }

    fun tryAgain(){
        startActivity(Intent(applicationContext, StartActivity::class.java))
        finish()
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean{
        if(event.action == KeyEvent.ACTION_DOWN){
            when(event.keyCode){
                KeyEvent.KEYCODE_BACK -> return true
            }
        }
        return super.dispatchKeyEvent(event)
    }
}
