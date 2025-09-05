package com.example.commandepompe

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import android.telephony.SmsManager

class MainActivity : AppCompatActivity() {

    private val requestSmsPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> if (!granted) Toast.makeText(this, "Permission SMS refusée", Toast.LENGTH_LONG).show() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val phone = prefs.getString("phone", null)
        if (phone.isNullOrEmpty()) askPhone()

        findViewById<Button>(R.id.btnOn).setOnClickListener { sendSms("1234#ON#") }
        findViewById<Button>(R.id.btnOff).setOnClickListener { sendSms("1234#OFF#") }
        findViewById<Button>(R.id.btnChange).setOnClickListener { askPhone() }
    }

    private fun ensureSmsPermission(): Boolean {
        val granted = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
        if (!granted) requestSmsPermission.launch(Manifest.permission.SEND_SMS)
        return granted
    }

    private fun sendSms(message: String) {
        if (!ensureSmsPermission()) return
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val phone = prefs.getString("phone", null)
        if (phone.isNullOrEmpty()) { askPhone(); return }
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phone, null, message, null, null)
            Toast.makeText(this, "SMS envoyé à $phone", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Erreur envoi SMS: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun askPhone() {
        val edit = EditText(this)
        edit.hint = getString(R.string.enter_phone_hint)
        AlertDialog.Builder(this).setTitle(getString(R.string.enter_phone_title)).setView(edit)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                val entered = edit.text.toString().trim()
                if (entered.isNotEmpty()) PreferenceManager.getDefaultSharedPreferences(this).edit().putString("phone", entered).apply() else Toast.makeText(this, "Numéro invalide", Toast.LENGTH_SHORT).show()
            }.setNegativeButton(getString(R.string.cancel), null).setCancelable(false).show()
    }
}