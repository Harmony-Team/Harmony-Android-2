package dev.timatifey.harmony.screen.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.timatifey.harmony.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}