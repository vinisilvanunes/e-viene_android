package com.example.eviene.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eviene.CircleTransform
import com.example.eviene.R
import com.example.eviene.models.User
import com.squareup.picasso.Picasso

class UserAdapter(
    private var users: List<User>,
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View, private val onItemClick: (User) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val profilePicture: ImageView = itemView.findViewById(R.id.search_profile_picture)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val username: TextView = itemView.findViewById(R.id.username)

        fun bind(user: User) {
            name.text = user.name
            username.text = user.username
            itemView.setOnClickListener { onItemClick(user) }
            try {
                Picasso.get()
                    .load(user.profilePicture)
                    .placeholder(R.drawable.baseline_account_circle_24) // Imagem padrão
                    .error(R.drawable.baseline_account_circle_24) // Imagem em caso de erro
                    .transform(CircleTransform())
                    .into(profilePicture)
            } catch (e: Exception) {
                Picasso.get()
                    .load(R.drawable.baseline_account_circle_24)
                    .placeholder(R.drawable.baseline_account_circle_24) // Imagem padrão
                    .error(R.drawable.baseline_account_circle_24) // Imagem em caso de erro
                    .transform(CircleTransform())
                    .into(profilePicture)
            }
        }
    }
}
