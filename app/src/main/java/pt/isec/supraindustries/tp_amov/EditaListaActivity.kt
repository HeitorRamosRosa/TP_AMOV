package pt.isec.supraindustries.tp_amov

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pt.isec.supraindustries.tp_amov.Data.Lista
import pt.isec.supraindustries.tp_amov.Data.Produto

class EditaListaActivity: AppCompatActivity(){


    lateinit var lV : ListView
    lateinit var tV : TextView
    //var lista: Lista = l
    var lista: Lista
    var p1: Produto
    var p2: Produto

    init{
        lista = Lista("judaspog")
        p1 = Produto("bom dia")
        p2 = Produto("boa tarde")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_lista)


        lV = findViewById(R.id.itemList1)
        tV = findViewById(R.id.listName1)

        lista.addProduto(p1)
        lista.addProduto(p2)

        lV.adapter = lVAdapter(this)
        tV.text = lista.nome

    }

    inner class lVAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        init{
            mContext = context
        }

        override fun getCount(): Int {
            return lista.produtoList?.size
        }

        override fun getItem(position: Int): Any {
            return lista.produtoList?.get(position)?.toString()!!
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = lista.produtoList[position].toString()
            return textView
        }

    }
}