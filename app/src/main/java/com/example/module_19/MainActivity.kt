package com.example.module_19

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarState
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menu = findViewById<Button>(R.id.menu)
        menu.setOnClickListener {
            Toast.makeText(this, "Главное меню", Toast.LENGTH_SHORT).show()
        }
        val favorites = findViewById<Button>(R.id.favourites)
        favorites.setOnClickListener {
            Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
        }
        val watch_later = findViewById<Button>(R.id.watch_later)
        watch_later.setOnClickListener {
            Toast.makeText(this, "Посмотреть позже!", Toast.LENGTH_SHORT).show()
        }
        val compilations = findViewById<Button>(R.id.compilations)
        compilations.setOnClickListener {
            Toast.makeText(this, "Подборки фильмов , сериалов", Toast.LENGTH_SHORT).show()
        }
        val setting = findViewById<Button>(R.id.settings)
        setting.setOnClickListener {
            Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
        }
    }
}



