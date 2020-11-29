package pt.isec.supraindustries.tp_amov

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.isec.supraindustries.tp_amov.Data.Lista
import pt.isec.supraindustries.tp_amov.Data.Produto


class MostrarListasActivity : AppCompatActivity(),ListAdapter.OnItemClickListener {

    var produtoList: MutableList<Produto>? = null
    lateinit var removeButton: Button
    lateinit var arrayListas: ArrayList<Lista>
    lateinit var removeEt: EditText
    lateinit var r : RecyclerView
    private val m = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostar_listas)

        arrayListas = arrayListOf(Lista("Lista0"),Lista("Lista1"),Lista("Lista2"),Lista("Lista3"),Lista("Lista4"))

        r = findViewById(R.id.itemList)
        removeButton = findViewById(R.id.removeButton)
        removeEt = findViewById(R.id.removeEt)


        r.apply {
            layoutManager = LinearLayoutManager(this@MostrarListasActivity)
            adapter = ListAdapter(arrayListas,m)
        }
    }

    fun onRemoveButtonClicked(view: View) {
        if(!removeEt.text.toString().isEmpty()){
            var pos: Int = Integer.parseInt(removeEt.text.toString())
            if(pos < arrayListas.size && pos > -1){
                arrayListas.removeAt(pos)
                r.apply {
                    adapter?.notifyItemRemoved(pos)
                }
            }
        }

    }
    

    override fun onItemClick(pos: Int) {
        Toast.makeText(this, "to new screen $pos", Toast.LENGTH_SHORT).show()
        val clickedItem : Lista = arrayListas[pos]
        r.apply {
            adapter?.notifyItemChanged(pos)
        }
    }
}