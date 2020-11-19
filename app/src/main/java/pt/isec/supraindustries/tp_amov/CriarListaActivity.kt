package pt.isec.supraindustries.tp_amov

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CriarListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_area_desenho)

        val strTitulo = intent.getStringExtra("titulo") ?: "Unnamed"
        val red = intent.getIntExtra("red",0)
        val green = intent.getIntExtra("green",0)
        val blue = intent.getIntExtra("blue",0)

        supportActionBar?.title = strTitulo

        findViewById<View>(android.R.id.content).setBackgroundColor(Color.rgb(red,green,blue))


    }
}