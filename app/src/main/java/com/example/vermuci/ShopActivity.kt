package com.example.vermuci

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_shop.*

const val EXTRA_COINS = "COINS"
const val EXTRA_ACTION = "ACTION"

class ShopActivity : AppCompatActivity() {

    private var coins = 0
    private var action = 0

    private var shop2 = false
    private var shop3 = false
    private var shop4 = false

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val settings = getSharedPreferences(EXTRA_PREFS, Context.MODE_PRIVATE)
        coins = settings.getInt(EXTRA_COINS, 0)
        action = settings.getInt(EXTRA_ACTION, 1)
        shop2 = settings.getBoolean("SHOP2", false)
        shop3 = settings.getBoolean("SHOP3", false)
        shop4 = settings.getBoolean("SHOP4", false)

        coins_text.text = "" + coins

        if(shop2)
            unlock2.visibility = View.GONE
        if(shop3)
            unlock3.visibility = View.GONE
        if(shop4)
            unlock4.visibility = View.GONE

        shopP1.setOnClickListener {
            action = 1
            val editor = settings.edit()
            editor.putInt(EXTRA_ACTION, action)
            editor.apply()
            startActivity(Intent(this@ShopActivity, StartActivity::class.java))
        }

        shopP2.setOnClickListener {
            when {
                shop2 -> {
                    action = 2
                    val editor = settings.edit()
                    editor.putInt(EXTRA_ACTION, action)
                    editor.apply()
                    startActivity(Intent(this@ShopActivity, StartActivity::class.java))
                }
                coins >= 30 -> {
                    shop2 = true
                    action = 2
                    coins -= 30

                    coins_text.text = "" + coins
                    unlock2.visibility = View.GONE

                    val editor = settings.edit()
                    editor?.putInt(EXTRA_ACTION, action)
                    editor?.putInt(EXTRA_COINS, coins)
                    editor?.putBoolean("SHOP2", shop2)
                    editor?.apply()
                    startActivity(Intent(this@ShopActivity, StartActivity::class.java))
                }
                else -> Snackbar.make(it, "Not enough coins", Snackbar.LENGTH_SHORT).show()
            }
        }

        shopP3.setOnClickListener {
            when {
                shop3 -> {
                    action = 3
                    val editor = settings.edit()
                    editor.putInt(EXTRA_ACTION, action)
                    editor.apply()
                    startActivity(Intent(this@ShopActivity, StartActivity::class.java))
                }
                coins >= 50 -> {
                    shop3 = true
                    action = 3
                    coins -= 50

                    coins_text.text = "" + coins
                    unlock2.visibility = View.GONE

                    val editor = settings.edit()
                    editor.putInt(EXTRA_ACTION, action)
                    editor.putInt(EXTRA_COINS, coins)
                    editor.putBoolean("SHOP3", shop3)
                    editor.apply()
                    startActivity(Intent(this@ShopActivity, StartActivity::class.java))
                }
                else -> Snackbar.make(it, "Not enough coins", Snackbar.LENGTH_SHORT).show()
            }
        }

        shopP4.setOnClickListener {
            when {
                shop4 -> {
                    action = 4
                    val editor = settings.edit()
                    editor.putInt(EXTRA_ACTION, action)
                    editor.apply()
                    startActivity(Intent(this@ShopActivity, StartActivity::class.java))
                }
                coins >= 80 -> {
                    shop4 = true
                    action = 4
                    coins -= 80

                    coins_text.text = "" + coins
                    unlock2.visibility = View.GONE

                    val editor = settings.edit()
                    editor.putInt(EXTRA_ACTION, action)
                    editor.putInt(EXTRA_COINS, coins)
                    editor.putBoolean("SHOP4", shop4)
                    editor.apply()
                    startActivity(Intent(this@ShopActivity, StartActivity::class.java))
                }
                else -> Snackbar.make(it, "Not enough coins", Snackbar.LENGTH_SHORT).show()
            }
        }

        home_button.setOnClickListener {
            startActivity(Intent(this@ShopActivity, StartActivity::class.java))
            finish()
        }

    }
}
