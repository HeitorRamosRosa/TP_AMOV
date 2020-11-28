package pt.isec.supraindustries.tp_amov

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.isec.supraindustries.tp_amov.Data.Lista


class MostrarListasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostar_listas)

        val listas : ArrayList <Lista> = arrayListOf(Lista("Lista1"),Lista("Lista2"),Lista("Lista3"),Lista("Lista4"),Lista("Lista5"),)

        val r : RecyclerView = findViewById(R.id.itemList)

        r.apply {
            layoutManager = LinearLayoutManager(this@MostrarListasActivity)
            adapter = ListAdapter(listas)
        }
    }

}