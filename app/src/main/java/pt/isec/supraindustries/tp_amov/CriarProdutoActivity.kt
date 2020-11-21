package pt.isec.supraindustries.tp_amov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import pt.isec.supraindustries.tp_amov.R

class CriarProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_produto)

        Log.i(TAG, "CriarProdutoActivity started")
    }
}