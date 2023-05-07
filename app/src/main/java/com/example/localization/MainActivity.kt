package com.example.localization

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var lang= arrayOf("English","hindi","Gujarati","Korean")
    var langcode= arrayOf("en","hi","gu","ko")

    lateinit var preferences:SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = getSharedPreferences("My",0)
        setLocale(preferences.getString("code","en")!!)
        Log.e(TAG, "onCreate: ===== "+preferences.getString("code","en") )
        setContentView(R.layout.activity_main)

        editor = preferences.edit()

        findViewById<Button>(R.id.change).setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Select Language")
            dialog.setSingleChoiceItems(lang,0,object :OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    editor.putString("code",langcode[which])
                    editor.commit()
                    startActivity(Intent(this@MainActivity,MainActivity::class.java))
                    finish()
                }
            })
             dialog.show()

        }

    }
    fun setLocale(code:String) {
        var local = Locale(code)
        Locale.setDefault(local)
        var resorce = resources
        var config = resorce.configuration
        config.setLocale(local)
        resorce.updateConfiguration(config,resorce.displayMetrics)
    }

}