package com.example.andersenhw5

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.andersenhw5.databinding.FragmentContactListBinding


private const val CONTACT_ID = "contactId"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_SURNAME = "contactSurname"
private const val CONTACT_PHONE = "contactPhone"

private var contacts = ArrayList<Contact>()

class ContactListFragment : Fragment() {
//    private lateinit var binding: FragmentContactListBinding
    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy { requireArguments().getString(CONTACT_NAME, "") }
    private val contactSurname: String by lazy { requireArguments().getString(CONTACT_SURNAME, "") }
    private val contactPhone: String by lazy { requireArguments().getString(CONTACT_PHONE, "") }
    private lateinit var contactClicked: ContactClicked

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = FragmentContactListBinding.inflate(inflater)

        val names = resources.getStringArray(R.array.names)
        val surnames = resources.getStringArray(R.array.surnames)

        for (id in 1..100) {
            contacts.add(
                Contact(
                    id,
                    names[id - 1],
                    surnames[id - 1],
                    "${id * (1000..100000).random()}",
                    "https://picsum.photos/id/$id/200/200"
                )
            )
        }

        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val adapter = ContactListAdapter { contact ->
            contactClicked.onContactClicked(
                contact.id, contact.name, contact.surname, contact.phone, contact.photo
            )
        }
        recyclerView.adapter = adapter


        if (arguments?.isEmpty == false) {
            val contact = contacts[contactId - 1]
            contact.name = contactName
            contact.surname = contactSurname
            contact.phone = contactPhone
        }
        adapter.setData(contacts)
        Log.d("!!!", "$contacts")


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ContactClicked) {
            contactClicked = context
        } else {
            throw ClassCastException("$context must implement ContactClickedListener")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            contactId: Int?, contactName: String?, contactSurname: String?, contactPhone: String?
        ) = ContactListFragment().apply {
            if (contactId != null && contactName != null) {
                arguments = Bundle().apply {
                    putInt(CONTACT_ID, contactId)
                    putString(CONTACT_NAME, contactName)
                    putString(CONTACT_SURNAME, contactSurname)
                    putString(CONTACT_PHONE, contactPhone)
                }
            } else {
                ContactListFragment()
            }
        }
    }

//    private fun setContactInfo(contact: Contact) {
//        contact.name = contactName
//        contact.surname = contactSurname
//        contact.phone = contactPhone
//    }
}

interface ContactClicked {
    fun onContactClicked(
        contactId: Int,
        contactName: String,
        contactSurname: String,
        contactPhone: String,
        contactPhoto: String
    )
}




