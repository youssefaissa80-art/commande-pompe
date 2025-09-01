package com.example.commandepompe

import android.content.Context
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnOn: Button
    private lateinit var btnOff: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOn = findViewById(R.id.btnOn)
        btnOff = findViewById(R.id.btnOff)

        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        var phoneNumber = prefs.getString("phone", null)

        if (phoneNumber == null) {
            askPhoneNumber { number ->
                prefs.edit().putString("phone", number).apply()
                phoneNumber = number
            }
        }

        btnOn.setOnClickListener {
            sendSMS(phoneNumber, "1234#ON#")
        }

        btnOff.setOnClickListener {
            sendSMS(phoneNumber, "1234#OFF#")
        }

        btnOn.setOnLongClickListener {
            askPhoneNumber { number ->
                prefs.edit().putString("phone", number).apply()
                phoneNumber = number
            }
            true
        }
    }

    private fun sendSMS(phone: String?, message: String) {
        if (phone == null) return
        SmsManager.getDefault().sendTextMessage(phone, null, message, null, null)
    }

    private fun askPhoneNumber(callback: (String) -> Unit) {
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Entrer le numéro")
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                callback(editText.text.toString())
            }
            .setCancelable(false)
            .show()
    }
}
