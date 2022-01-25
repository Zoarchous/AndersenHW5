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
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactListFragment.newInstance(
                null,null,null,null
            )
            replace(R.id.main, fragment)
            commit()
        }
        setContentView(binding.root)
    }
}