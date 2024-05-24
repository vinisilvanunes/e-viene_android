package com.example.eviene.models

import java.sql.Date

data class Post (
    var _id: Int,
    var _idUser: Int,
    var foto: String,
    var nome: String,
    var texto: String,
    var image: String,
    var date: Date
    )
