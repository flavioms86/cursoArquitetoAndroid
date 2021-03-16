package com.example.appcommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class UserProfileActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var titleTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        titleTxt = findViewById(R.id.toolbar_title)
        titleTxt.text = getString(R.string.user_profile_title)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}