package com.example.app01

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val message = intent.getStringExtra(PreviewActivity.EXTRA_MESSAGE)
        textView.text = message

        btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse(String.format("mailto:%s", getString(R.string.defaultEmail)))
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.defaultSubject))
            intent.putExtra(Intent.EXTRA_TEXT, textView.text.toString())

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, getString(R.string.noEmailAppFound), Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "extra:message"

        fun start(activity: Activity, message: String) {
            val intent = Intent(activity, PreviewActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, message)
            activity.startActivity(intent)

        }
    }
}
