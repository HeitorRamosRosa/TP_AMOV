package pt.isec.supraindustries.tp_amov.Data

import java.io.Serializable

data class Lista(var nome: String) : Serializable {
    var lista : HashMap<Produto, Int> = HashMap<Produto, Int>()

    fun addProduto(produto: Produto, quantidade: Int){
        lista.set(produto, quantidade)
    }

    fun removeProduto(produto: Produto){
        lista.remove(produto)
    }

    fun setName(name : String){
        nome = name
    }
}