package br.com.borarachar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert.ACTION
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import br.com.borarachar.model.Conta
import java.text.NumberFormat

class Calculo : AppCompatActivity() {
    private var valor = 0.0
    private var valorImagem = 0
    private var valorGorjeta = 0
    private var qtdAmigo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo)

        val buttonCalcular: Button = findViewById(R.id.buttonCalcular)
        buttonCalcular.setOnClickListener { recebeValorServico() }
        val buttonAddGorjeta: Button = findViewById(R.id.buttonAdicionaGorjeta)
        buttonAddGorjeta.setOnClickListener { adicionarValorGorjeta(buttonAddGorjeta.isClickable) }
        val buttonDiminuirGorjeta: Button = findViewById(R.id.buttonDiminuirGorjeta)
        buttonDiminuirGorjeta.setOnClickListener { diminuirValorGorjeta(buttonDiminuirGorjeta.isClickable) }

        val buttonDiminuirAmigo: Button = findViewById(R.id.buttonDiminuirAmigo)
        buttonDiminuirAmigo.setOnClickListener { diminuirAmigo(buttonDiminuirAmigo.isClickable) }
        val buttonAdicionarAmigo: Button = findViewById(R.id.buttonAdicionarAmigo)
        buttonAdicionarAmigo.setOnClickListener { adicionarAmigo(buttonAdicionarAmigo.isClickable) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_calculo,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_info ->{
              //  Toast.makeText(applicationContext,"Desenvolvido por Ful&t",Toast.LENGTH_LONG).show()
                val intent = Intent(this, Calculadora::class.java)
                startActivity(intent)
                true
            }
            R.id.action_freepik ->{
               val url = "http://google.com.br"
                intent =  Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)

                true
            }
            R.id.action_sair ->{
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun recebeValorServico() {
        val valorServico: EditText = findViewById(R.id.valorServico)
        //converte em double a string gorjeta
        val convertValor = valorServico.text.toString().toDoubleOrNull()
        if (convertValor == null || convertValor == 0.0) {
            Toast.makeText(this, R.string.insira_valor_da_conta_text, Toast.LENGTH_SHORT).show()
            return
        } else {
            valor = convertValor
            calcularGorjeta()
        }
    }

    private fun adicionarValorGorjeta(clickable: Boolean) {
        if (clickable) {
            if (valorImagem < 3) {
                valorImagem++
                val imageViewVazio: ImageView = findViewById(R.id.imageViewVazio)
                val drawableGorjeta = when (valorImagem) {
                    0 -> R.drawable.gorjeta_0
                    1 -> R.drawable.gorjeta_1
                    2 -> R.drawable.gorjeta_2
                    else -> R.drawable.gorjeta_3
                }
                imageViewVazio.setImageResource(drawableGorjeta)
                val text: TextView = findViewById(R.id.porcentagem)
                val d = when (drawableGorjeta) {
                    R.drawable.gorjeta_0 -> 0
                    R.drawable.gorjeta_1 -> 5
                    R.drawable.gorjeta_2 -> 10
                    else -> 15
                }
                text.text = d.toString()
                valorGorjeta = d
            }
            if (valorImagem == 3) {
                Toast.makeText(this, R.string.limite_maximo_gorjeta_text, Toast.LENGTH_SHORT).show()
                return
            }
        }

    }

    private fun diminuirValorGorjeta(clickable: Boolean) {
        if (clickable) {
            if (valorImagem > 0) {
                valorImagem--
                val imageViewVazio: ImageView = findViewById(R.id.imageViewVazio)
                val drawableGorjeta = when (valorImagem) {
                    0 -> R.drawable.gorjeta_0
                    1 -> R.drawable.gorjeta_1
                    2 -> R.drawable.gorjeta_2
                    else -> R.drawable.gorjeta_2
                }
                imageViewVazio.setImageResource(drawableGorjeta)
                val text: TextView = findViewById(R.id.porcentagem)
                val d = when (drawableGorjeta) {
                    R.drawable.gorjeta_0 -> 0
                    R.drawable.gorjeta_1 -> 5
                    R.drawable.gorjeta_2 -> 10
                    else -> 15
                }
                text.text = d.toString()
                valorGorjeta = d
            }
        }

    }

    private fun adicionarAmigo(clickable: Boolean) {
        if (clickable) {
            qtdAmigo++
            val textQtdAmigo: TextView = findViewById(R.id.qtdAmigos)
            textQtdAmigo.text = qtdAmigo.toString()
        }
    }

    private fun diminuirAmigo(clickable: Boolean) {
        val textQtdAmigo: TextView = findViewById(R.id.qtdAmigos)
        if (clickable) {
            if (qtdAmigo > 2) {
                qtdAmigo--
                textQtdAmigo.text = qtdAmigo.toString()
            } else {
                textQtdAmigo.setText(R.string.quantos_amigos_text)
                qtdAmigo = 1
            }
        }
    }

    private fun calcularGorjeta() {
        val c = Conta()
        var gorjeta = c.calcularGorjeta(valor, valorGorjeta)
        var valorTotalConta = c.valorTotalConta(valor, gorjeta)

        val arredondarValor: SwitchCompat = findViewById(R.id.switchArredondarValor)
        if (arredondarValor.isChecked) {
            gorjeta = kotlin.math.ceil(gorjeta)
            valorTotalConta = kotlin.math.ceil(valorTotalConta)

        }
        var valorTotalPorAmigo = c.valorContaPorAmigo(valorTotalConta, qtdAmigo)
        displayGorjeta(gorjeta, valorTotalConta, valorTotalPorAmigo)

    }

    private fun displayGorjeta(gorjetaTotal: Double, valorTotalConta: Double,
        valorTotalContaPAmigo: Double
    ) {
        val vgt = NumberFormat.getCurrencyInstance().format(gorjetaTotal)
        val textGorjeta: TextView = findViewById(R.id.valorGorjeta)
        textGorjeta.text = vgt.toString()

        val vtc = NumberFormat.getCurrencyInstance().format(valorTotalConta)
        val textValorTotalConta: TextView = findViewById(R.id.valorTotalConta)
        textValorTotalConta.text = vtc.toString()

        val vtcpa = NumberFormat.getCurrencyInstance().format(valorTotalContaPAmigo)
        val textValorTotalContaPAmigo: TextView = findViewById(R.id.valorTotalContaPAmigo)
        textValorTotalContaPAmigo.text = vtcpa.toString()


    }
}

