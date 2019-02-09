package com.example.vermuci

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        play_btn.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        shop_btn.setOnClickListener {
            startActivity(Intent(applicationContext, ShopActivity::class.java))
        }

        more.setOnClickListener {
            val popupMenu = PopupMenu(this@StartActivity, more)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                var intent: Intent
                val chooser: Intent
                val id = item?.itemId
                if (id == R.id.feedback) {
                    intent = Intent(Intent.ACTION_SEND)
                    intent.data = Uri.parse("mailto:")
                    val to = arrayOf("pozhidaev.yury98@gmail.com")
                    intent.putExtra(Intent.EXTRA_EMAIL, to)
                    intent.type = "message/rfc822"
                    chooser = Intent.createChooser(intent, "Send Feedback")
                    startActivity(chooser)
                }

                if(id == R.id.share){
                    intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Vermuci")
                    var sAux = "\n Let me recommend you this game\n\n"
                    sAux += "https://vk.com/thiss_id"
                }
                true
            }
            popupMenu.show()
        }
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
