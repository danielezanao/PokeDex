package br.com.up.pokedex.extension

import br.com.up.pokedex.model.Pokemon

fun Pokemon.id(): String{
    val paths = url.split("/")
    return paths[paths.size - 2]
}