package pt.isec.supraindustries.tp_amov.Activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.R

class CriarProdutoActivity : AppCompatActivity() {

    lateinit var productList : ArrayList<Produto>
    lateinit var categoryList : ArrayList<Categoria>
    lateinit var unitList : ArrayList<Unidade>

    override fun onResume() {
        super.onResume()
        val intent = this.intent

        productList = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>
        categoryList = intent.getSerializableExtra("listaCategorias") as ArrayList<Categoria>
        unitList =  intent.getSerializableExtra("listaUnidades") as ArrayList<Unidade>
        Log.i("DEBUG","onResume_CriarProdutoActivity:\nproduct list initialized. Size: ${productList.size}")

        atualizaSpinners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_produto)
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

        val unitSpinner = findViewById<Spinner>(R.id.cp_sProductUnit)
        val categorySpinner  = findViewById<Spinner>(R.id.cp_sProductCategory)
        val unitSelectedIndex = unitSpinner.selectedItemPosition
        val categorySelectedIndex = categorySpinner.selectedItemPosition

        var unitSelected : Unidade? = null
        var categorySelected : Categoria? = null
        if(unitSelectedIndex>0)
            unitSelected = unitList[unitSelectedIndex-1]
        if(categorySelectedIndex>0)
            categorySelected = categoryList[categorySelectedIndex-1]

        var temp = Produto(nome, marca, unitSelected, categorySelected ,notas)
        productList.add(temp)

        val returnIntent = this.intent
        returnIntent.putExtra("listaProdutos",productList)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }

    fun atualizaSpinners(){
        val unitSpinner = findViewById<Spinner>(R.id.cp_sProductUnit)
        val categorySpinner  = findViewById<Spinner>(R.id.cp_sProductCategory)

        var categorias : ArrayList<String> = arrayListOf("N/A")
        var unidades : ArrayList<String> = arrayListOf("N/A")


        for(unidade in unitList){
            unidades.add(unidade.nome)
        }
        for(categoria in categoryList){
            categorias.add(categoria.nome)
        }

        unitSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,unidades)
        categorySpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,categorias)

    }


}