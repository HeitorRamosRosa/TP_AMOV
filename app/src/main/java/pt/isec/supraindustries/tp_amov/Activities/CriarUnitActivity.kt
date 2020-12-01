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
import pt.isec.supraindustries.tp_amov.Data.Unidade
import pt.isec.supraindustries.tp_amov.R

const val TAG = "INFOTAG"
class CriarUnitActivity : AppCompatActivity() {

    lateinit var unitList :  ArrayList<Unidade>

    override fun onResume() {
        super.onResume()

        val intent = this.intent
        unitList = intent.getSerializableExtra("listaUnidades") as ArrayList<Unidade>

        Log.i(pt.isec.supraindustries.tp_amov.TAG, "onResume: CriarUnitAcitivty")
        Log.i(pt.isec.supraindustries.tp_amov.TAG, "UnitListSize: ${unitList.size}")


        val listview = findViewById<ListView>(R.id.cu_UnitList)
        listview.adapter = unitsAdatper(unitList, this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_unit)

        val intent = this.intent
        unitList = intent.getSerializableExtra("listaUnidades") as ArrayList<Unidade>

        Log.i(pt.isec.supraindustries.tp_amov.TAG, "onCreate: CriarUnitAcitivty")
        Log.i(pt.isec.supraindustries.tp_amov.TAG, "UnitListSize: ${unitList.size}")

    }

    fun Save(view: View){
        val returnIntent = Intent ()
        returnIntent.putExtra("listaUnidades",unitList)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }

    fun criaUnidade(view: View)
    {
        Log.i(TAG,"On: CriaUnidade")
        val unitName = findViewById<EditText>(R.id.etUnitName).text.toString()
        val unitSymbol = findViewById<EditText>(R.id.etUnitSymbol).text.toString()

        if(unitName.isEmpty() || unitSymbol.isEmpty())
        {
            Toast.makeText(this, "Fill the mandatory fields", Toast.LENGTH_SHORT).show()
            return
        }
        val temp = Unidade(unitName, unitSymbol)
        unitList.add(temp)

        val listview = findViewById<ListView>(R.id.cu_UnitList)
        listview.adapter = unitsAdatper(unitList, this)

        Log.i("DEBUG_CriUnitAct_criaU", "listaUnidades Size: ${unitList.size}")
    }

    private class unitsAdatper(ul : ArrayList<Unidade>, myContext : Context) : BaseAdapter()
    {
        private val mContext :Context

        init{
            mContext = myContext
        }
        var unitlist = ul

        override fun getItem(p0: Int): Any {
            return "TEST STRING"
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //responsible for how many rows in list
        override fun getCount(): Int {
            return unitlist.size
        }

        //responsible for rendering each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row_unit, viewGroup, false)

            Log.i("DEBUG_CREATE_UNIT","Position on row: ${position}")
            val unitName = row.findViewById<TextView>(R.id.row_unitname)
            val unitSymbol = row.findViewById<TextView>(R.id.row_unitsymbol)
            unitName.append(" "+unitlist[position].nome)
            unitSymbol.append(" "+unitlist[position].simbolo)

            return row
        }



    }

}