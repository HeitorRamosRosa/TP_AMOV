package pt.isec.supraindustries.tp_amov.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import pt.isec.supraindustries.tp_amov.Data.Lista
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.R

class EditaListaActivity: AppCompatActivity(){

    lateinit var lV : ListView
    lateinit var tV : TextView
    lateinit var option : Spinner
    lateinit var bAddProduto : Button
    var sPos : Int = -1
    var lPos = 0;
    lateinit var lista: Lista
    var p1: Produto
    var p2: Produto
    var lista2: Lista
    lateinit var adapter : ArrayAdapter<Produto>
    lateinit var lVA: lVAdapter
    lateinit var productList : ArrayList<Produto>
    lateinit var tempProductList : ArrayList<Produto>
    lateinit var tempQtList : ArrayList<Int>
    lateinit var lists : ArrayList<Lista>
    lateinit var eTQt : EditText
    var posList : Int = -1

    init{
        lista2 = Lista("TEST")
        p1 = Produto("bom dia")
        p2 = Produto("boa tarde")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_lista)
        tempProductList = ArrayList<Produto>(0)
        tempQtList =  ArrayList<Int>(0)

        productList = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>
        lists = intent.getSerializableExtra("lists") as ArrayList<Lista>
        posList = intent.getSerializableExtra("posList") as Int
        lista = lists[posList]
        eTQt = findViewById(R.id.eItemQuantity)
        lV = findViewById(R.id.itemList1)
        tV = findViewById(R.id.listName1)
        option = findViewById(R.id.el_sItemUnit) as Spinner
        bAddProduto = findViewById(R.id.buttonAddItem)

        for((key , value) in lista.lista){
            tempProductList.add(key)
            tempQtList.add(value)
        }

        lVA = lVAdapter(this)
        lV.adapter = lVA

        lV.setOnItemClickListener { parent, view, position, id ->
            lista.lista.remove(tempProductList.get(position))
            tempProductList.removeAt(position)
            tempQtList.removeAt(position)

            lVA.notifyDataSetChanged()
        }

        tV.text = lista.nome


        if(option != null){
            adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,productList)
            option.adapter = adapter
        }
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sPos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    fun onCriarProduto(view: View){
        val intent = Intent(this, CriarProdutoActivity::class.java)
        startActivity(intent)
    }

    inner class lVAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        init{
            mContext = context
        }

        override fun getCount(): Int {
            return tempProductList.size
        }

        override fun getItem(position: Int): Any {
            return tempProductList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = tempProductList[position].toString()
            return textView
        }

    }
    fun onAddButtonClicked(view: View){
        var x : Int
        if(!eTQt.text.toString().isEmpty()){
            x = Integer.parseInt(eTQt.text.toString())
        }else{
            x = -1
        }

        if(x > 0){
            tempProductList.add((productList[sPos]))
            tempQtList.add(x)
            lVA.notifyDataSetChanged()
        }
    }

    fun Save(view: View)
    {
        val nome = findViewById<EditText>(R.id.etProductName).text.toString()
        if(nome.isEmpty()){
            Toast.makeText(this, "Fill the mandatory fields", Toast.LENGTH_SHORT).show()
            return
        }

        val marca = findViewById<EditText>(R.id.etProductBrand).text.toString()
        val notas = findViewById<EditText>(R.id.etProductNotes).text.toString()

        val returnIntent = this.intent
        returnIntent.putExtra("listaProdutos",productList)
        returnIntent.putExtra("lists",lists)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }

}