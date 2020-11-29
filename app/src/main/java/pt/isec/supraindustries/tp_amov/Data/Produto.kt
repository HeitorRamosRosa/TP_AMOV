package pt.isec.supraindustries.tp_amov.Data

import java.io.Serializable

data class Produto(var nome: String, var marca: String="", var quantidade : Float=0f) : Serializable {
    var preco = 0f
    var unidade: Unidade? = null
    var comprado = false
    var notas: String? = null
}