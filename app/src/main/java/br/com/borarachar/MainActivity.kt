package br.com.borarachar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
		     //enable button voltar
        ActionBar.DISPLAY_HOME_AS_UP

        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        val intent = Intent(this, Calculo::class.java)
        imageButton.setOnClickListener { startActivity(intent) }
    }
}