package pt.isec.supraindustries.tp_amov

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import pt.isec.supraindustries.tp_amov.Activities.CriarListaActivity
import pt.isec.supraindustries.tp_amov.Activities.CriarUnitActivity
import pt.isec.supraindustries.tp_amov.Activities.MostrarListasActivity
import pt.isec.supraindustries.tp_amov.Data.Unidade

const val TAG = "DEBUG"

class MainActivity : AppCompatActivity() {


    var unidadeLista = ArrayList<Unidade>(0)

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
        Log.i(TAG, "unidadeLista size: ${unidadeLista.size}")
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
        startActivity(intent)
    }

    fun onMostrarListas(view: View){
        val intent = Intent(this, MostrarListasActivity::class.java)
        startActivity(intent)
    }

    fun onEditarUnits(view: View){
        val intent = Intent(this,CriarUnitActivity::class.java)
        intent.putExtra("listaUnidades",unidadeLista)
        startActivityForResult(intent,101)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Log.i("DEBUG","Getting Lista Unidades")
                unidadeLista = data?.getSerializableExtra("listaUnidades") as ArrayList<Unidade>
            }
        }
    }


    
}