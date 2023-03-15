package com.example.taskonjsonapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.taskonjsonapplication.R
import com.example.taskonjsonapplication.SERIES_FRAGMENT
import com.example.taskonjsonapplication.presentation.series.SeriesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SeriesFragment>(R.id.fragment_container_view, SERIES_FRAGMENT)
            }
        }
    }
}