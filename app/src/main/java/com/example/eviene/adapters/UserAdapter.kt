package com.example.eviene.adapters


import android.util.Log
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

class UserAdapter(private var users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = user.name
        holder.username.text = user.username
        try {
            Picasso.get()
                .load(user.profilePicture)
                .placeholder(R.drawable.baseline_account_circle_24) // Imagem padrão
                .error(R.drawable.baseline_account_circle_24) // Imagem em caso de erro
                .transform(CircleTransform())
                .into(holder.profilePicture)
        } catch (e: Exception){
            Picasso.get()
                .load(R.drawable.baseline_account_circle_24)
                .placeholder(R.drawable.baseline_account_circle_24) // Imagem padrão
                .error(R.drawable.baseline_account_circle_24) // Imagem em caso de erro
                .transform(CircleTransform())
                .into(holder.profilePicture)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePicture: ImageView = itemView.findViewById(R.id.search_profile_picture)
        val name: TextView = itemView.findViewById(R.id.name)
        val username: TextView = itemView.findViewById(R.id.username)
    }
}
