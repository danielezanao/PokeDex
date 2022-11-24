package br.com.up.pokedex.network

import br.com.up.pokedex.model.PokeDetails
import br.com.up.pokedex.model.PokeListApiResponse
import br.com.up.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    private var retrofit:Retrofit? = null
    private var service:PokeApiService? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        service = retrofit?.create(PokeApiService::class.java)
    }

    fun getPokemons(listener:(pokemons:List<Pokemon>?) -> Unit){

        service?.getPokemons()?.enqueue(

            object : Callback<PokeListApiResponse>{
                override fun onResponse(
                    call: Call<PokeListApiResponse>,
                    response: Response<PokeListApiResponse>
                ) {
                    listener(response.body()?.pokemons)
                }

                override fun onFailure(call: Call<PokeListApiResponse>, t: Throwable) {
                    listener(null)
                }
            }
        )
    }

    fun getPokemonByName(name:String,
                         listener: (Pokemon?) -> Unit){

        val call = service?.getPokemonByName(name)

        call?.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>,
                                    response: Response<Pokemon>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<Pokemon>,
                                   t: Throwable) {
                listener(null)
            }
        })

    }

    fun getPokemonDetailsById(id:String, listener: (PokeDetails?) -> Unit) {
        val call = service?.getPokemonById(id)

        call?.enqueue(object : Callback<PokeDetails>{
            override fun onResponse(call: Call<PokeDetails>,
                                    response: Response<PokeDetails>) {
                listener(response.body())
            }

            override fun onFailure(call: Call<PokeDetails>,
                                   t: Throwable) {
                listener(null)
            }
        })

    }
}