package pt.isec.supraindustries.tp_amov.Data

import java.io.Serializable

data class Lista(var nome: String) : Serializable {
    var produtoList = mutableListOf<Produto>()

    fun addProduto(produto: Produto) {
        produtoList?.add(produto)
    }
}