package br.com.up.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.com.up.pokedex.extension.id
import br.com.up.pokedex.network.Api
import com.squareup.picasso.Picasso

class PokemonDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)


        val nome : TextView = findViewById(R.id.detail_nome)
        val imagem : ImageView = findViewById(R.id.detail_imagem)
        val tipo : TextView = findViewById(R.id.detail_tipos)
        val stats : TextView = findViewById(R.id.detail_stats)
        val habilidades : TextView = findViewById(R.id.detail_habilidades)
        val movimentos : TextView = findViewById(R.id.detail_movimentos)

        val id = intent.getStringExtra("id")
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
        Picasso.get().load(url).into(imagem)

        Api().getPokemonDetailsById(id!!){
                details ->

            if(details != null){

                var tipoStr = "Tipo: "
                var statsStr = "Status:\n"
                var habilidadeStr = "Abilidades:\n"
                var movimentosStr = "Ataques:\n"

                nome.text = "Nome: " + details.name

                details.types.forEach {
                    tipoStr = tipoStr + it.type.name + "  "
                }
                tipo.text = tipoStr

                details.stats.forEach{
                    statsStr = statsStr + it.stat.name + ": " + it.base_stat + "\n"
                }
                stats.text = statsStr

                details.abilities.forEach {
                    habilidadeStr = habilidadeStr + it.ability.name + "  "
                }
                habilidades.text = habilidadeStr

                details.moves.forEach {
                    movimentosStr = movimentosStr + it.move.name + "\n"
                }
                movimentos.text = movimentosStr
            }
        }

    }
}