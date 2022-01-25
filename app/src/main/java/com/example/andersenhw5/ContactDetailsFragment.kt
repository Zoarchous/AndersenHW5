package com.example.andersenhw5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andersenhw5.databinding.FragmentContactDetailsBinding

private const val CONTACT_ID = "contactId"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_SURNAME = "contactSurname"
private const val CONTACT_PHONE = "contactPhone"

class ContactDetailsFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailsBinding
    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy { requireArguments().getString(CONTACT_NAME, "") }
    private val contactSurname: String by lazy { requireArguments().getString(CONTACT_SURNAME, "") }
    private val contactPhone: String by lazy { requireArguments().getString(CONTACT_PHONE, "") }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)
        binding.nameEditText.setText(contactName)
        binding.surnameEditText.setText(contactSurname)
        binding.phoneEditText.setText(contactPhone)

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val surname = binding.surnameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(
            contactId: Int, contactName: String, contactSurname: String, contactPhone: String
        ) = ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTACT_ID, contactId)
                    putString(CONTACT_NAME, contactName)
                    putString(CONTACT_SURNAME, contactSurname)
                    putString(CONTACT_PHONE, contactPhone)
                }
        }
    }
}