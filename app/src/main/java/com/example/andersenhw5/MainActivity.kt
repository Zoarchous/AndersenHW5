package com.example.andersenhw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.andersenhw5.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentManager.beginTransaction().run {
            val contactListFragment = ContactListFragment
            replace(main, contactListFragment)
        }
    }
}