package pt.isec.supraindustries.tp_amov.Activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.R

class CriarProdutoActivity : AppCompatActivity() {

    lateinit var productList : ArrayList<Produto>


    override fun onResume() {
        super.onResume()
        val intent = this.intent

        productList = intent.getSerializableExtra("listaProdutos") as ArrayList<Produto>

        Log.i("DEBUG","onResume_CriarProdutoActivity:\nproduct list initialized. Size: ${productList.size}")
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

        var temp = Produto(nome, marca, null, null ,notas)
        productList.add(temp)

        val returnIntent = this.intent
        returnIntent.putExtra("listaProdutos",productList)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }


}