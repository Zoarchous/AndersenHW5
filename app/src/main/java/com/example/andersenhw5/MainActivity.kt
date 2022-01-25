package com.example.andersenhw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.andersenhw5.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContactClicked, ContactDetailsSave {
    private lateinit var binding: ActivityMainBinding
    private val container = R.id.container
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactListFragment.newInstance(
                null,null,null,null
            )
            replace(container, fragment)
            commit()
        }
        setContentView(binding.root)
    }

    override fun onSaveButtonClicked(
        contactId: Int,
        contactName: String,
        contactSurname: String,
        contactPhone: String
    ) {
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactListFragment.newInstance(
                contactId, contactName, contactSurname, contactPhone
            )
            replace(container, fragment)
            commit()
        }
    }

    override fun onContactClicked(
        contactId: Int,
        contactName: String,
        contactSurname: String,
        contactPhone: String,
        contactPhoto: String
    ) {
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactDetailsFragment.newInstance(
                contactId, contactName, contactSurname, contactPhone, contactPhoto
            )
            replace(container, fragment)
            commit()
        }
    }
}