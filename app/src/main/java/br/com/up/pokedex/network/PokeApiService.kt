package br.com.up.pokedex.network

import br.com.up.pokedex.model.PokeDetails
import br.com.up.pokedex.model.PokeListApiResponse
import br.com.up.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon?limit=151")
    fun getPokemons():Call<PokeListApiResponse>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id:String) : Call<PokeDetails>

    @GET("pokemon/{pokemonName}")
    fun getPokemonByName(@Path("pokemonName") pokemon:String) : Call<Pokemon>
}