package com.mm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Activiy 확장함수

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}

