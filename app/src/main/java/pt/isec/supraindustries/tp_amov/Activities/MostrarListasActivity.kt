package pt.isec.supraindustries.tp_amov.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.isec.supraindustries.tp_amov.Activities.EditaListaActivity
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Lista
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.ListAdapter
import pt.isec.supraindustries.tp_amov.R


class   MostrarListasActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {

    var produtoList: MutableList<Produto>? = null
    lateinit var removeButton: Button
    lateinit var arrayListas: ArrayList<Lista>
    lateinit var removeEt: EditText
    lateinit var r : RecyclerView
    lateinit var productList : ArrayList<Produto>
    lateinit var lists : ArrayList<Lista>
    private val m = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_listas)

        productList = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>
        lists = intent.getSerializableExtra("lists") as ArrayList<Lista>
        arrayListas = lists
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
        //val clickedItem : Lista = arrayListas[pos]
        r.apply {
            adapter?.notifyItemChanged(pos)
        }
        val intent = Intent(this, EditaListaActivity::class.java)
        intent.putExtra("listaProdutos",productList)
        intent.putExtra("lists",lists)
        intent.putExtra("posList",pos)
        startActivityForResult(intent, 105)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==105) //pode atualizar lists ou productlist
        {
            if(resultCode == Activity.RESULT_OK)
            {
                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>
                lists = data?.getSerializableExtra("lists") as ArrayList<Lista>
                Log.i("DEBUG_MostraLista","Getting produtos or lists. Size: ${productList.size} && ${lists[0].lista.size}")
            }
        }
    }

    fun Save(view: View)
    {
        Log.i("DEBUG_MostraLista","Save: Sending produtos and lists. Size: ${productList.size} && ${lists[0].lista.size}")
        val returnIntent = this.intent
        returnIntent.putExtra("listaProdutos",productList)
        returnIntent.putExtra("lists",lists)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }

}