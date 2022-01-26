package com.example.andersenhw5

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ContactListAdapter(private val onClick: (Contact) -> Unit):
    RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    private var contactsList = emptyList<Contact>()

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(contact: Contact){
            val contactImage: ImageView = view.findViewById(R.id.contactPhotoImageView)
            val contactName: TextView = view.findViewById(R.id.contactNameTextView)


            Picasso.get()
                .load(contact.photo)
                .into(contactImage)

            contactName.text = contact.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactsList[position]
        holder.bind(contact)
        holder.itemView.setOnClickListener { onClick(contact) }
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(contactList: List<Contact>){
        this.contactsList = contactList
        notifyDataSetChanged()
    }
}