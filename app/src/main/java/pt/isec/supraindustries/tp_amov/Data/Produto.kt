package pt.isec.supraindustries.tp_amov.Data

import java.io.Serializable

data class Produto(var nome: String, var marca: String="", var unidade: Unidade?=null, var notas: String = "" ) : Serializable {
    var preco: Array<Float> = arrayOf()
    var comprado: Boolean = false


}