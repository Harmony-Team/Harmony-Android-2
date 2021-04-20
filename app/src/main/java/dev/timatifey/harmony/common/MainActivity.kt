package dev.timatifey.harmony.common

import android.os.Bundle
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Harmony)
        setContentView(R.layout.activity_main)
    }

}