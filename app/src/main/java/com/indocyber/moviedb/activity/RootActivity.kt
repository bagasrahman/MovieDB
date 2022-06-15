package com.indocyber.moviedb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity_layout)
    }
}