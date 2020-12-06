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
import android.widget.*
import org.w3c.dom.Text
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.R
import pt.isec.supraindustries.tp_amov.Data.Produto

class EditaProdutoActivity : AppCompatActivity() {

    lateinit var productList : ArrayList<Produto>
    lateinit var categoryList : ArrayList<Categoria>
    lateinit var unitList : ArrayList<Unidade>
    var pPos : Int = -1

    override fun onResume() {
        super.onResume()

        val intent = this.intent
        categoryList = intent.getSerializableExtra("listaCategorias") as ArrayList<Categoria>
        unitList = intent.getSerializableExtra("listaUnidades")as ArrayList<Unidade>

        val listview = findViewById<ListView>(R.id.ep_lvProdutos)
        listview.adapter = produtoAdapter(productList,this)

        listview.setOnItemClickListener { parent, view, position, id ->
            Log.i("DEBUG_EditProduto","clicked on item, position: $position")
            setContentView(R.layout.edita_single_produto)
            pPos = position
            atualizaSingleProdutoVista()
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_produto)

        //o product list tem de ser inicializado no onCreate e nao pode ser no  onResume, porque ao criar um produto, este vai ser modificado, ou seja
        //se voltar a iniciar pelo que recebeu por intent, vai ficar desatualizado. Tem de inicializar por aqui uma vez quando a atividade começa, e outra vez pelo onActivity result
        productList  = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>
/*
        val intent = this.intent
        productList  = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>
        categoryList = intent.getSerializableExtra("listaCategorias") as ArrayList<Categoria>
        unitList = intent.getSerializableExtra("listaUnidades")as ArrayList<Unidade>


        Log.i("DEBUG_EditProd", "onCreate: EditarProdutoAcitivty")
        Log.i("DEBUG_EditProd", "ProdListSize: ${productList.size}")

        val lv = findViewById<ListView>(R.id.ep_lvProdutos)
        lv.setOnItemClickListener { parent, view, position, id ->
            Log.i("DEBUG_EditProduto","clicked on item, position: $position")
            setContentView(R.layout.edita_single_produto)
            pPos = position
            atualizaSingleProdutoVista()
        }
        */
    }

    private fun atualizaSingleProdutoVista() {
        val et_Name = findViewById<EditText>(R.id.ep_etProductName)
        val et_Brand = findViewById<EditText>(R.id.ep_etProductBrand)
        val et_Notes = findViewById<EditText>(R.id.ep_productNotes)

        var produtoEditar = productList[pPos]
        var pUnitIndex : Int = -1
        var pCategoryIndex : Int = -1
        var counter : Int = 0
        et_Name.setText(produtoEditar.nome)
        et_Brand.setText(produtoEditar.marca)
        et_Notes.setText(produtoEditar.notas)

        //por unidades e categoria no spinner
        //descobrir qual a unidade e categoria set para o produto e po-las como o default escolhido

        var s_Unit = findViewById<Spinner>(R.id.ep_sUnit)
        var s_Category = findViewById<Spinner>(R.id.ep_sCategory)
        var unidades : ArrayList<String> = arrayListOf("N/A")
        var categorias : ArrayList<String> = arrayListOf("N/A")

        for(unidade in unitList){
            unidades.add(unidade.nome)
            if(unidade == produtoEditar.unidade)
                pUnitIndex = counter
            counter++
        }
        counter = 0

        for(categoria in categoryList){
            categorias.add(categoria.nome)
            if(categoria == produtoEditar.categoria)
                pCategoryIndex = counter
            counter++
        }
        counter = 0

        s_Unit.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unidades)
        s_Category.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categorias)
        s_Unit.setSelection(pUnitIndex+1)
        s_Category.setSelection(pCategoryIndex+1)

        atualizaPrecos()

        val lv = findViewById<ListView>(R.id.ep_lvPrecos)
        lv.setOnItemClickListener { parent, view, position, id ->
            productList[pPos].remPreco(position)
            atualizaPrecos()
        }
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
            productName.text = productList[position].nome
            productBrand.text = productList[position].marca

            return row
        }
    }

    fun onCriarProduto(view: View)
    {
        val intent = Intent(this, CriarProdutoActivity::class.java)
        intent.putExtra("listaProdutos", productList)
        intent.putExtra("listaCategorias", categoryList)
        intent.putExtra("listaUnidades", unitList)
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

    fun spBack(view: View)
    {
        setContentView(R.layout.activity_edita_produto)

        val listview = findViewById<ListView>(R.id.ep_lvProdutos)
        listview.adapter = produtoAdapter(productList,this)


        val lv = findViewById<ListView>(R.id.ep_lvProdutos)
        lv.setOnItemClickListener { parent, view, position, id ->
            categoryList = intent.getSerializableExtra("listaCategorias") as ArrayList<Categoria>
            unitList = intent.getSerializableExtra("listaUnidades")as ArrayList<Unidade>
            Log.i("DEBUG_EditProduto","clicked on item, position: $position")
            setContentView(R.layout.edita_single_produto)
            pPos = position
            atualizaSingleProdutoVista()
        }

    }

    fun spSave(view: View)
    {
        val et_Name = findViewById<EditText>(R.id.ep_etProductName)
        val et_Brand = findViewById<EditText>(R.id.ep_etProductBrand)
        val et_Notes = findViewById<EditText>(R.id.ep_productNotes)

        //fazer processamento de gravar unidades e categorias
        val unitIndex = findViewById<Spinner>(R.id.ep_sUnit).selectedItemPosition
        val categoryIndex = findViewById<Spinner>(R.id.ep_sCategory).selectedItemPosition
        if(unitIndex == 0)
            productList[pPos].unidade = null
        else
        {
            productList[pPos].unidade = unitList[unitIndex-1]
        }
        if(categoryIndex == 0)
            productList[pPos].categoria = null
        else
        {
            productList[pPos].categoria = categoryList[categoryIndex-1]
        }

        productList[pPos].nome = et_Name.text.toString()
        productList[pPos].marca = et_Brand.text.toString()
        productList[pPos].notas = et_Notes.text.toString()

        spBack(view)
    }

    fun addPreco(view: View)
    {
        val etPreco = findViewById<EditText>(R.id.ed_Preco)
        if(etPreco.text.isEmpty()){
            Toast.makeText(this,"Insert price",Toast.LENGTH_SHORT).show()
            return
        }
        val pPreco = etPreco.text.toString().toFloat()
        Log.i("DEBUG","Precof: ${pPreco}")
        productList[pPos].addPreco(pPreco)
        atualizaPrecos()
    }

    fun atualizaPrecos(){
        val lv = findViewById<ListView>(R.id.ep_lvPrecos)
        lv.adapter = precoAdapter(productList[pPos],this)
    }

    private class precoAdapter(produto: Produto, myContext : Context): BaseAdapter() {
        val p = produto
        val  context = myContext
        override fun getCount(): Int {
            Log.i("DEBUG","SIIZE: ${p.precos.size}")
            return p.precos.size
        }

        override fun getItem(p0: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(context)
            val row = layoutInflater.inflate(R.layout.row_preco, viewGroup, false)

            val pPrice = row.findViewById<TextView>(R.id.row_price)

            pPrice.setText(p.precos[position].toString())

            return row
        }

    }
}