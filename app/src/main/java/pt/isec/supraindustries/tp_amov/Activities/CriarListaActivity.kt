package pt.isec.supraindustries.tp_amov.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Lista
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.R

class CriarListaActivity : AppCompatActivity() {


    lateinit var productList : ArrayList<Produto>
    lateinit var categoryList : ArrayList<Categoria>
    lateinit var unitList : ArrayList<Unidade>
    lateinit var comprasList : ArrayList<Lista>

    var tempLista : Lista = Lista("temp")

    override fun onResume() {
        super.onResume()
        val intent = this.intent
        productList = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>
        categoryList = intent.getSerializableExtra("listaCategorias") as ArrayList<Categoria>
        unitList = intent.getSerializableExtra("listaUnidades") as ArrayList<Unidade>
        comprasList = intent.getSerializableExtra("listaCompras") as ArrayList<Lista>
        Log.i("DEBUG","onResume_CriarListaActivity:\nComprasSize: ${comprasList.size}")
        atualizaSpinner()
        atualizaListaItems()
    }

    private fun atualizaListaItems() {
        val lv = findViewById<ListView>(R.id.cl_lvItems)
        val produtosLista  = ArrayList<Produto>()
        val quantidadeLista = ArrayList<Int>()

        for((produto,quantidade) in tempLista.lista)
        {
            produtosLista.add(produto)
            quantidadeLista.add(quantidade)
        }
        lv.adapter = itemAdapter(produtosLista,quantidadeLista, this)
    }

    private class itemAdapter(produtosLista: ArrayList<Produto>,quantidadesLista:ArrayList<Int>, myContext : Context): BaseAdapter() {
        private val mContext :Context

        init{
            mContext = myContext
        }
        private val pList = produtosLista
        private val qList = quantidadesLista

        override fun getCount(): Int {
            return pList.size
        }

        override fun getItem(p0: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup??): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row_produto_quantidade, viewGroup, false)

            var iName = row.findViewById<TextView>(R.id.row_pq_tvProductName)
            var iBrand = row.findViewById<TextView>(R.id.row_pq_tvProductBrand)
            var iQuantity = row.findViewById<TextView>(R.id.row_pq_tvProductQuantity)

            iName.text = pList[position].nome
            iBrand.text = pList[position].marca
            iQuantity.text = qList[position].toString()

            return row
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_lista)
    }

    fun onCriarProduto(view: View){
        val intent = Intent(this, CriarProdutoActivity::class.java)
        intent.putExtra("listaProdutos", productList)
        intent.putExtra("listaCategorias", categoryList)
        intent.putExtra("listaUnidades", unitList)
        startActivityForResult(intent,103)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==103)
        {
            if(resultCode== RESULT_OK){
                //isto vai levar para o main ecran sem deixar fazer mais nada, dar handle a isto
                //por exemplo, gravar a lista com o novo item antes de volta
                    //OU, ao acabar de criar produto adiciona-se o produto ao spinner e nao se da "finish(), dando apenas o finish com o result noutra actividade

                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>

                Log.i("DEBUG","onCriarListaActivity: onActivityResult - productList size: ${productList.size}")
            }
        }
    }

    fun atualizaSpinner(){
        val spinner = findViewById<Spinner>(R.id.sItemProduto)
        var pNomes : ArrayList<String> = arrayListOf()

        Log.i("DEBUG","inAtualizaSpinner, productList size: ${productList.size}")
        for(produto in productList){
            pNomes.add(produto.nome+"|"+produto.marca)
        }

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pNomes)

    }

    fun addItem(view: View)
    {
        if(findViewById<EditText>(R.id.cl_etItemQuantity).text.isEmpty()){
            Toast.makeText(this,"Please insert product quantity.",Toast.LENGTH_SHORT).show()
            return
        }
        //get posicao do spinner para encontrar o produto a adicionar a templist;
        //adicionar o produto a temp list;
        //atualizar itemlist;
        val spinner = findViewById<Spinner>(R.id.sItemProduto)

        val pIndex = spinner.selectedItemPosition
        val pQuantity = findViewById<EditText>(R.id.cl_etItemQuantity).text.toString().toInt()
        tempLista.addProduto(productList[pIndex],pQuantity)

        atualizaListaItems()
    }

    fun SaveList(view: View)
    {
        comprasList.add(tempLista)
        val returnIntent = this.intent
        intent.putExtra("listaProdutos",productList)
        intent.putExtra("listaCompras",comprasList)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }
}