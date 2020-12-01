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
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.R
import pt.isec.supraindustries.tp_amov.Data.Produto

class EditaProdutoActivity : AppCompatActivity() {

    lateinit var productList : ArrayList<Produto>


    override fun onResume() {
        super.onResume()

        Log.i("DEBUG_EditProd", "onResume: EditarProdutoAcitivty")
        Log.i("DEBUG_EditProd", "ProdListSize: ${productList.size}")


        val listview = findViewById<ListView>(R.id.ep_lvProdutos)
        listview.adapter = produtoAdapter(productList,this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_produto)

        val intent = this.intent
        productList  = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>

        Log.i("DEBUG_EditProd", "onCreate: EditarProdutoAcitivty")
        Log.i("DEBUG_EditProd", "ProdListSize: ${productList.size}")
    }

    private class produtoAdapter (pl : ArrayList<Produto>, myContext : Context) : BaseAdapter()
    {
        private val mContext :Context

        init{
            mContext = myContext
        }
        var productList = pl

        override fun getCount(): Int {
            return  productList.size
        }

        override fun getItem(p0: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row_produto, viewGroup, false)

            Log.i("DEBUG_CREATE_UNIT","Position on row: ${position}")
            val productName = row.findViewById<TextView>(R.id.row_tvProductName)
            val productBrand= row.findViewById<TextView>(R.id.row_tvProductBrand)
            var temp = productList[position]
            productName.text = productList[position].nome
            productBrand.text = productList[position].marca

            return row
        }

    }

    fun onCriarProduto(view: View)
    {
        val intent = Intent(this, CriarProdutoActivity::class.java)
        intent.putExtra("listaProdutos", productList)
        startActivityForResult(intent, 203)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==203) //atualiza produto list
        {
            if(resultCode == Activity.RESULT_OK)
            {
                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>
                Log.i("DEBUG_EditProd","Getting Lista Produtos. Size: ${productList.size}")
            }
        }
    }

    fun onSaveChanges(view: View)
    {
        val returnIntent = Intent ()
        returnIntent.putExtra("listaProdutos",productList)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }
}