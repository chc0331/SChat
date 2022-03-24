package com.example.myapplication.toyproject.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.toyproject.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("chc__", "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d("chc__", "onResume");
    }

    override fun onStop() {
        super.onStop()
        Log.d("chc__", "onStop");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("chc__", "onDestroy")
    }
}