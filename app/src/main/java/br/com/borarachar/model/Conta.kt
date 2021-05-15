package br.com.borarachar.model

class Conta {

    fun calcularGorjeta(a: Double, b: Int): Double {
        val divisor = 100
        return (a * b) / divisor
    }

    fun valorTotalConta(a: Double, b: Double): Double {
        return a + b
    }

    fun valorContaPorAmigo(valorTotalConta: Double, qtdAmigo: Int): Double {
        return(valorTotalConta / qtdAmigo)
    }
}