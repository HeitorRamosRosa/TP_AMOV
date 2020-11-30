package pt.isec.supraindustries.tp_amov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.R

class CriarProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_produto)

        Log.i(pt.isec.supraindustries.tp_amov.TAG, "CriarProdutoActivity started")
    }

    fun onCriaProduto(){
        var nome = findViewById<EditText>(R.id.etProductName)
        if(nome.length()==0 || nome.equals(" ")){
            Log.i(pt.isec.supraindustries.tp_amov.TAG,"Impossivel Criar Produto com nome vazio.\n")
            return;
        }
        Log.i(pt.isec.supraindustries.tp_amov.TAG, "Criando Produto ${nome.text.toString()}")
        var produto = Produto(nome.text.toString())
    }
}