package com.example.eviene.models

import java.io.Serializable
import java.io.StringBufferInputStream

class Post (
    val _id: String,
    var foto: String,
    var nome: String,
    var texto: String,
    var image: String
    ): Serializable {
    }
