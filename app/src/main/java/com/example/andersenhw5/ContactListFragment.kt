package com.example.andersenhw5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andersenhw5.databinding.FragmentContactListBinding

private const val CONTACT_ID = "contactId"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_SURNAME = "contactSurname"
private const val CONTACT_PHONE = "contactPhone"

private var contacts = arrayOf(
    Contact(1, "Kate", "Ali", "123123123"),
    Contact(2, "Arthur", "Shcherba", "123456789"),
    Contact(3,"Dima","Petrov", "1112222333"),
    Contact(4, "Dasha", "Petrova", "444555666")
)
 class ContactListFragment : Fragment() {
    private lateinit var binding: FragmentContactListBinding
    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy { requireArguments().getString(CONTACT_NAME, "") }
    private val contactSurname: String by lazy { requireArguments().getString(CONTACT_SURNAME, "") }
    private val contactPhone: String by lazy { requireArguments().getString(CONTACT_PHONE, "") }
    private lateinit var contactClicked: ContactClicked

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater)
        binding.contact1.text = contacts[0].name
        binding.contact2.text = contacts[1].name
        binding.contact3.text = contacts[2].name
        binding.contact4.text = contacts[3].name

        if (arguments?.isEmpty == false){
            when(contactId) {
                1 -> {
                    val contact = contacts[0]
                    setContactInfo(contact)
                }
                2 -> {
                    val contact = contacts[1]
                    setContactInfo(contact)
                }
                3 -> {
                    val contact = contacts[2]
                    setContactInfo(contact)
                }
                4 -> {
                    val contact = contacts[3]
                    setContactInfo(contact)
                }
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contact1.setOnClickListener {
            val contact = contacts[0]
            contactClicked.onContactClicked(contact.id,contact.name,contact.surname, contact.phone)
        }
        binding.contact2.setOnClickListener {
            val contact = contacts[1]
            contactClicked.onContactClicked(contact.id,contact.name,contact.surname, contact.phone)
        }
        binding.contact3.setOnClickListener {
            val contact = contacts[2]
            contactClicked.onContactClicked(contact.id,contact.name,contact.surname, contact.phone)
        }
        binding.contact4.setOnClickListener {
            val contact = contacts[3]
            contactClicked.onContactClicked(contact.id,contact.name,contact.surname, contact.phone)
        }
    }
     override fun onAttach(context: Context) {
         super.onAttach(context)
         if (context is ContactClicked) {
             contactClicked = context
         } else {
             throw ClassCastException("$context must implement ContactClickedListener")
         }
     }

    companion object{
        @JvmStatic
        fun newInstance (
            contactId: Int?, contactName: String?, contactSurname: String?, contactPhone: String?
        ) = ContactListFragment().apply {
            if (contactId != null && contactName != null) {
                arguments = Bundle().apply {
                    putInt(CONTACT_ID, contactId)
                    putString(CONTACT_NAME, contactName)
                    putString(CONTACT_SURNAME, contactSurname)
                    putString(CONTACT_PHONE, contactPhone)
                }
            }else{
                    ContactListFragment()
                }
            }
        }
     private fun setContactInfo (contact: Contact){
         contact.name = contactName
         contact.surname = contactSurname
         contact.phone = contactPhone
     }
 }

interface ContactClicked {
    fun onContactClicked(contactId: Int, contactName: String, contactSurname: String, contactPhone: String)
}




