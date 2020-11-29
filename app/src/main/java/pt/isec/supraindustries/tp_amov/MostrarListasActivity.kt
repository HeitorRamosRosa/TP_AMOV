package pt.isec.supraindustries.tp_amov

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.isec.supraindustries.tp_amov.Data.Lista
import pt.isec.supraindustries.tp_amov.Data.Produto


class MostrarListasActivity : AppCompatActivity() {

    var produtoList: MutableList<Produto>? = null
    lateinit var removeButton: Button
    lateinit var arrayListas: ArrayList<Lista>
    lateinit var removeEt: EditText
    lateinit var r : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostar_listas)

        arrayListas = arrayListOf(Lista("Lista1"),Lista("Lista2"),Lista("Lista3"),Lista("Lista4"),Lista("Lista5"),)

        r = findViewById(R.id.itemList)
        removeButton = findViewById(R.id.removeButton)
        removeEt = findViewById(R.id.removeEt)


        r.apply {
            layoutManager = LinearLayoutManager(this@MostrarListasActivity)
            adapter = ListAdapter(arrayListas)
        }
    }

    fun onRemoveButtonClicked(view: View) {
        var pos: Int = Integer.parseInt(removeEt.text.toString())
        arrayListas.removeAt(pos)
        r.apply {
            adapter?.notifyDataSetChanged()
        }

    }
}