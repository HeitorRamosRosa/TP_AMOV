package pt.isec.supraindustries.tp_amov

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CriarListaActivity : AppCompatActivity() {

    lateinit var option : Spinner
    lateinit var result : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "here here ")
        super.onCreate(savedInstanceState)
        Log.i(TAG, "here here ")
        setContentView(R.layout.activity_criar_lista)
        Log.i(TAG, "here here ")
        option = findViewById(R.id.itemsExistentes) as Spinner
        result = findViewById(R.id.itemsListados) as TextView
        Log.i(TAG, "here here ")
        var options = arrayOf("");

        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

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
        val intent = Intent(this,CriarProdutoActivity::class.java)
        startActivity(intent)
    }
}