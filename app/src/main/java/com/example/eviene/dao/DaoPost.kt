package com.example.eviene.dao

import java.util.ArrayList
import com.example.eviene.models.Post

class DaoPost {
    fun listaContatos(): ArrayList<Post> {
        val list:ArrayList<Post> = ArrayList()
        list.add(Post("1", "", "Myke", "Só sei que nada sei",""))


        return list
    }
}