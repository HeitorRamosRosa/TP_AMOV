package pt.isec.supraindustries.tp_amov.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.R

class CriarListaActivity : AppCompatActivity() {

    lateinit var option : Spinner
    lateinit var result : TextView

    lateinit var productList : ArrayList<Produto>

    override fun onResume() {
        super.onResume()
        val intent = this.intent
        productList = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>
        Log.i("DEBUG","onResume_CriarListaActivity:\nproduct list initialized. Size: ${productList.size}")
        atualizaSpinner()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_lista)
        option = findViewById<Spinner>(R.id.sItemProduto)
        result = findViewById<TextView>(R.id.itemsListados)
        var options = arrayOf("");

        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        //ja nao tenho a certeza o que abaixo faz, comentar e ver se faz  diferença
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result.text = options.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = "Please select an item."
            }
        }
    }

    fun onCriarProduto(view: View){
        val intent = Intent(this, CriarProdutoActivity::class.java)
        intent.putExtra("listaProdutos", productList)
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
                val returnIntent = this.intent
                intent.putExtra("listaProdutos",productList)
                setResult(Activity.RESULT_OK,returnIntent)
                finish()
            }
        }
    }

    fun atualizaSpinner(){
        val spinner = findViewById<Spinner>(R.id.sItemProduto)
        var pNomes : ArrayList<String> = arrayListOf()

        Log.i("DEBUG","inAtualizaSpinner, productList size: ${productList.size}")
        for(produto in productList){
            pNomes.add(produto.nome)
        }

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pNomes)

    }
}