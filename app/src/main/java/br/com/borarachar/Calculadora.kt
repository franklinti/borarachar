package br.com.borarachar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Calculadora : AppCompatActivity(), View.OnClickListener {

    lateinit var campoEditText: EditText
    var number = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)
        //inicia campo edittext
        campoEditText = findViewById(R.id.editTextCalc)
    }

    ////teste
    override fun onClick(view: View) {
        //recebe click dos buttons
        val clickButtonsNumerico = view as TextView
        //recebe o que foi digira no editext como uma string
        var textBtnClickValue = campoEditText.text.toString()
        //pega id dos buttons clicados
        if (textBtnClickValue.length < 15) {
            when (clickButtonsNumerico.id) {
                R.id.textBtn0 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "0"
                }
                R.id.textBtn9 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "9"
                }
                R.id.textBtn8 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "8"
                }
                R.id.textBtn7 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "7"
                }
                R.id.textBtn6 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "6"
                }
                R.id.textBtn5 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "5"
                }
                R.id.textBtn4 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "4"
                }
                R.id.textBtn3 -> {
                    //adiciona o texto a variavel textoconvertido
                    textBtnClickValue += "3"
                }
                R.id.textBtn2 -> {
                    textBtnClickValue += "2"
                }
                R.id.textBtn1 -> {
                    textBtnClickValue+= "1"
                }
                R.id.textBtnVirgula -> {
                    textBtnClickValue += "."
                }
            }
        } else {
            Toast.makeText(this, R.string.limite_numero, Toast.LENGTH_SHORT).show()
            return
        }
        //excreve o texto do button clicado no edittext
       campoEditText.setText(textBtnClickValue)

    }

    var op=""
    fun operacao(view: View) {
        //recebe click dos buttons
        val clickButtonOperacao = view as TextView
        var textBtnClickValue = campoEditText.text.toString()
        if(clickButtonOperacao.isClickable &&textBtnClickValue.equals("") ){
            //nao permiti executar insercao no campo
        }else {
            when (clickButtonOperacao.id) {
                R.id.textBtnDividir -> {
                    textBtnClickValue = "/"
                }
                R.id.textBtnMultiplicar -> {
                    textBtnClickValue = "x"
                }
            }
        }
            campoEditText.append(textBtnClickValue).toString()

    }

    fun clean(view: View) {
        campoEditText.setText("")
    }
}