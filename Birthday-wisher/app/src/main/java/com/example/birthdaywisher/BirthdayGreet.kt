package com.example.birthdaywisher

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_birthday_greet.*

class BirthdayGreet : AppCompatActivity() {

    companion object{
        const val name_extra = "name_extra"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_greet)
        val name = intent.getStringExtra("name_extra")
        birthdayGreeting.text = "Happy Birthday\n$name!"
    }
}