package com.example.eviene.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eviene.R
import com.example.eviene.models.Post

class ListaPostAdapter(private val posts: List<Post>) :
        RecyclerView.Adapter<ListaPostAdapter.PostViewHolder>() {
    var posicaoClicada:Int =-1


    class PostViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var foto:ImageView
        var nome:TextView
        var texto:TextView
        var image:TextView

        //var endereco:TextView? = null






        init {

            foto = v.findViewById(R.id.img_Foto)
            nome = v.findViewById(R.id.txt_Nome)
            texto = v.findViewById(R.id.txt_Texto)
            image = v.findViewById(R.id.img_Image)
        }






    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts,parent, false)
        return PostViewHolder(view)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val foto = holder.foto
        val nome = holder.nome
        val texto = holder.texto
        val image = holder.image


        nome.setText(posts.get(position).author.username)
        texto.setText(posts.get(position).description)

        holder.itemView.setOnLongClickListener { v->
            posicaoClicada = holder.adapterPosition
            Log.i("Menu", "onBindViewHolder: " + posicaoClicada)
            false
        }


    }

    override fun getItemCount(): Int {
        return posts.count()
    }


}


