package pt.isec.supraindustries.tp_amov

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import pt.isec.supraindustries.tp_amov.Activities.*
import pt.isec.supraindustries.tp_amov.Data.Categoria
import pt.isec.supraindustries.tp_amov.Data.Lista
import pt.isec.supraindustries.tp_amov.Data.Produto
import pt.isec.supraindustries.tp_amov.Data.Unidade

const val TAG = "DEBUG"

class MainActivity : AppCompatActivity() {


    var unitList = ArrayList<Unidade>(0)
    var categoryList = ArrayList<Categoria>(0)
    var productList = ArrayList<Produto>(0)
    var comprasList = ArrayList<Lista>(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        Log.i(TAG, "onCreate: MainActivity")
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG, "unidadeLista size: ${unitList.size}")
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onCriarLista(view: View){
        val intent = Intent(this, CriarListaActivity::class.java)
        intent.putExtra("listaProdutos",productList)
        intent.putExtra("listaCategorias",categoryList)
        intent.putExtra("listaUnidades",unitList)
        intent.putExtra("listaCompras",comprasList)
        startActivityForResult(intent, 105)
    }

    fun onMostrarListas(view: View){
        if(comprasList.isEmpty() == true){
            Toast.makeText(this, "There are no lists to show yet!", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent(this, MostrarListasActivity::class.java)
            intent.putExtra("listaProdutos",productList)
            intent.putExtra("lists",comprasList)
            startActivityForResult(intent, 104)
        }
    }

    fun onEditarUnits(view: View){
        val intent = Intent(this,CriarUnitActivity::class.java)
        intent.putExtra("listaUnidades",unitList)
        startActivityForResult(intent,101)
    }

    fun onEditarCategories(view: View){
        val intent = Intent(this, CriarCategoriaActivity::class.java)
        intent.putExtra("listaCategorias", categoryList)
        startActivityForResult(intent, 102)
    }

    fun onEditarProducts(view: View){
        val intent = Intent(this, EditaProdutoActivity::class.java)
        intent.putExtra("listaProdutos", productList)
        intent.putExtra("listaCategorias",categoryList)
        intent.putExtra("listaUnidades",unitList)
        startActivityForResult(intent, 103)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101) //atualiza unit list
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Log.i("DEBUG","Getting Lista Unidades")
                unitList = data?.getSerializableExtra("listaUnidades") as ArrayList<Unidade>
            }
        }
        if(requestCode==102) //atualiza category list
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Log.i("DEBUG","Getting Lista Categorias")
                categoryList = data?.getSerializableExtra("listaCategorias") as ArrayList<Categoria>
            }
        }
        if(requestCode==103) //atualiza produto list
        {
            if(resultCode == Activity.RESULT_OK)
            {
                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>
                Log.i("DEBUG","Getting Lista Produtos. Size: ${productList.size}")
            }
        }
        if(requestCode==104) //pode atualizar lists ou productlist
        {
            if(resultCode == Activity.RESULT_OK)
            {
                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>
                comprasList = data?.getSerializableExtra("lists") as ArrayList<Lista>
                Log.i("DEBUG","Getting produtos or lists. Size: ${productList.size} && ${comprasList.size}")
            }
        }
        if(requestCode==105) //atualiza lista de listas e productlist
        {
            if(resultCode == Activity.RESULT_OK)
            {
                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>
                comprasList = data?.getSerializableExtra("listaCompras") as ArrayList<Lista>
                Log.i("DEBUG","OnResult105_ListaProdutos. Size: ${productList.size}")
            }
            if(resultCode == 666){
                Log.i("DEBUG","OnResult666")
                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>
            }
            /*else if(resultCode == Activity.RESULT_CANCELED)
            {
                Log.i("DEBUG","OnResult105CANCELED")
                productList = data?.getSerializableExtra("listaProdutos") as ArrayList<Produto>
                Log.i("DEBUG","OnResult105CANCELED_ListaProdutos. Size: ${productList.size}")
            }*/
        }
    }


    
}