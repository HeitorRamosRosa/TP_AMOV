package pt.isec.supraindustries.tp_amov.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.R

class CriarCategoriaActivity : AppCompatActivity() {

    lateinit var categoryList : ArrayList<Categoria>

    override fun onResume() {
        super.onResume()

        val intent = this.intent
        categoryList = intent.getSerializableExtra("listaCategorias") as ArrayList<Categoria>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_categoria)

        val intent = this.intent
        categoryList = intent.getSerializableExtra("listaCategorias") as ArrayList<Categoria>


        val listview = findViewById<ListView>(R.id.lv_CategoryList)
        listview.adapter = categoryAdapter(categoryList, this)
    }

    private class categoryAdapter(cl: ArrayList<Categoria>, myContext : Context): BaseAdapter()
    {
        private val mContext :Context
        init{
            mContext = myContext
        }

        var categoryList = cl

        override fun getCount(): Int {
            return categoryList.size
        }

        override fun getItem(p0: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row_category, viewGroup, false)

            val categoryName = row.findViewById<TextView>(R.id.row_categoryname)
            categoryName.text = categoryList[position].nome

            return row
        }

    }

    fun criaCategoria(view: View)
    {
        val nome = findViewById<TextView>(R.id.etCategory).text.toString()

        if(nome.isEmpty())
        {
            Toast.makeText(this, "Fill the mandatory fields", Toast.LENGTH_SHORT).show()
            return
        }

        val temp = Categoria(nome)
        categoryList.add(temp)

        val  listview = findViewById<ListView>(R.id.lv_CategoryList)
        listview.adapter = categoryAdapter(categoryList,this)
    }

    fun Save(view: View)
    {
        val returnIntent = Intent ()
        returnIntent.putExtra("listaCategorias",categoryList)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }
}