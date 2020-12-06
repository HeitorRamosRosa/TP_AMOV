package pt.isec.supraindustries.tp_amov.Data

import java.io.Serializable

data class Produto(var nome: String, var marca: String="N/A", var unidade: Unidade?=null, var categoria: Categoria?=null, var notas: String = "" ) : Serializable {
    var precos: ArrayList<Float> = ArrayList<Float>(10)

    override fun toString(): String {
        var retString : String
        retString = nome +" | " + marca

        if(categoria != null){
            retString +=  " " +categoria!!.nome
        }
        return retString
    }

    fun addPreco(preco : Float){
        if(precos.size<10){
            precos.add(0,preco)
            return
        }
        precos.removeAt(9)
        precos.add(0,preco)
    }

    fun remPreco(index: Int){
        precos.removeAt(index)
    }
}