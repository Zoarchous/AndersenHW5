package com.example.andersenhw5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andersenhw5.databinding.FragmentContactDetailsBinding
import com.squareup.picasso.Picasso

private const val CONTACT_ID = "contactId"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_SURNAME = "contactSurname"
private const val CONTACT_PHONE = "contactPhone"
private const val CONTACT_PHOTO = "contactPhoto"


class ContactDetailsFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailsBinding
    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy { requireArguments().getString(CONTACT_NAME, "") }
    private val contactSurname: String by lazy { requireArguments().getString(CONTACT_SURNAME, "") }
    private val contactPhone: String by lazy { requireArguments().getString(CONTACT_PHONE, "") }
    private val contactPhoto: String by lazy { requireArguments().getString(CONTACT_PHOTO, "") }

    private lateinit var contactDetailsSave: ContactDetailsSave
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)
        binding.nameEditText.setText(contactName)
        binding.surnameEditText.setText(contactSurname)
        binding.phoneEditText.setText(contactPhone)

        Picasso.get()
            .load(contactPhoto)
            .into(binding.contactImage)
        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val surname = binding.surnameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            contactDetailsSave.onSaveButtonClicked(contactId, name, surname, phone)

        }
        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ContactDetailsSave) {
            contactDetailsSave = context
        } else {
            throw ClassCastException("$context must implement SaveChangesButtonClickListener")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            contactId: Int, contactName: String, contactSurname: String, contactPhone: String, contactPhoto: String
        ) = ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTACT_ID, contactId)
                    putString(CONTACT_NAME, contactName)
                    putString(CONTACT_SURNAME, contactSurname)
                    putString(CONTACT_PHONE, contactPhone)
                    putString(CONTACT_PHOTO, contactPhoto)
                }
        }
    }
}
interface ContactDetailsSave{
    fun onSaveButtonClicked (contactId: Int, contactName: String, contactSurname: String, contactPhone: String)
}