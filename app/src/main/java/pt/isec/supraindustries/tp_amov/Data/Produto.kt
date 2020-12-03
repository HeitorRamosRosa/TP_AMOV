package pt.isec.supraindustries.tp_amov.Data

import java.io.Serializable

data class Produto(var nome: String, var marca: String="N/A", var unidade: Unidade?=null, var categoria: Categoria?=null, var notas: String = "" ) : Serializable {
    var preco: Array<Float> = arrayOf()

    override fun toString(): String {
        return nome +": brand: " + marca + " unit: " + unidade + " category: " + categoria
    }
}