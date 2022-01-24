package com.example.andersenhw5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andersenhw5.databinding.FragmentContactListBinding
import kotlinx.android.synthetic.main.fragment_contact_list.*

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
abstract class ContactListFragment : Fragment() {
    private lateinit var binding: FragmentContactListBinding
    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy {requireArguments().getString(CONTACT_NAME, "")}
    private val contactSurname: String by lazy {requireArguments().getString(CONTACT_SURNAME, "")}
    private val contactPhone: String by lazy {requireArguments().getString(CONTACT_PHONE, "")}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater)
        contact1.text = contacts[0].name
        contact2.text = contacts[1].name
        contact3.text = contacts[2].name
        contact4.text = contacts[3].name


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    abstract fun contactClicked(
        contactId: Int, contactName: String, contactSurname: String, contactPhone: String)
}