package com.example.app02

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addSocialListeners()

        addEmailListener()

        addCopyright()
    }

    private fun addEmailListener() {
        btnSend.setOnClickListener {
//            intent = Intent(applicationContext, com.example.app01.PreviewActivity::class.java)
//            intent.component = ComponentName("com.example.app01", "com.example.app01.PreviewActivity")
            val intent = Intent("com.example.app01.action.PREVIEW_EMAIL")
            intent.putExtra("extra:message", message.text.toString())

/*
            val intent =
                packageManager.getLaunchIntentForPackage("com.example.app01.PreviewActivity")
            if (intent != null) {
                intent.putExtra("extra:message", message.text.toString())
            }
*/

            /*
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse(String.format("mailto:%s", getString(R.string.email)))
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSubject))
            intent.putExtra(Intent.EXTRA_TEXT, message.text.toString())
*/

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, getString(R.string.appNotFound), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun addSocialListeners() {
        addSocialListener(imgTelegram, "https://telegram.me/phatmn")
        addSocialListener(imgInstagram, "http://instagram.com/querbyris")
        addSocialListener(imgGithub, "http://github.com/phatmn")
    }

    private fun addSocialListener(view: View, url: String) {
        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "App not found", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun addCopyright() {
        if (social.parent is LinearLayout) {
            val disclaimer = TextView(this)
            disclaimer.text = Typography.copyright + " 2019 Andrey Korytin"
            disclaimer.gravity = Gravity.CENTER_HORIZONTAL
            (social.parent as LinearLayout).addView(disclaimer)
        }
    }


}
