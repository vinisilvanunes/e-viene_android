package com.example.eviene.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eviene.R
import com.example.eviene.models.Post
import android.util.Log

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImageView: ImageView = itemView.findViewById(R.id.img_Foto)
        val usernameTextView: TextView = itemView.findViewById(R.id.txt_Nome)
        val postTextView: TextView = itemView.findViewById(R.id.txt_Texto)
        val postImageView: ImageView = itemView.findViewById(R.id.img_Image)
        val likeButton: ImageButton = itemView.findViewById(R.id.btnLike)
        val commentButton: ImageButton = itemView.findViewById(R.id.btnComment)
        val infoButton: ImageButton = itemView.findViewById(R.id.btnInfo)
        val shareButton: ImageButton = itemView.findViewById(R.id.btnShare)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        val author = post.author

        Log.d("PostAdapter", "Post: $post")
        Log.d("PostAdapter", "Author: $author")

        // Carregar a imagem do perfil do usuário usando Glide
        Glide.with(holder.itemView.context)
            .load(author?.profilePicture)
            .placeholder(R.drawable.baseline_account_circle_24)
            .into(holder.profileImageView)

        // Definir o nome de usuário e texto do post
        holder.usernameTextView.text = author?.username ?: "Unknown"
        holder.postTextView.text = post.description


        // Verificar se há imagem no post e carregar usando Glide
        if (post.images.isNotEmpty()) {
            holder.postImageView.visibility = View.VISIBLE
            Glide.with(holder.itemView.context)
                .load(post.images)
                .into(holder.postImageView)
        } else {
            holder.postImageView.visibility = View.GONE
        }

        // Configurar botões de ação (like, comment, info, share) se necessário
        holder.likeButton.setOnClickListener {
            // Lógica para lidar com o botão de curtir
        }

        holder.commentButton.setOnClickListener {
            // Lógica para lidar com o botão de comentário
        }

        holder.infoButton.setOnClickListener {
            // Lógica para lidar com o botão de info
        }

        holder.shareButton.setOnClickListener {
            // Lógica para lidar com o botão de compartilhar
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
