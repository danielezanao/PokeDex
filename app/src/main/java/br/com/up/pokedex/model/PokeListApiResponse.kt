package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokeListApiResponse(
    @SerializedName("results")
    val pokemons:List<Pokemon>
)
