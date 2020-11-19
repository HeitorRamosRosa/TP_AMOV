package pt.isec.supraindustries.tp_amov.Data

data class Lista(var nome: String) {
    var produtoList: MutableList<Produto>? = null

    fun addProduto(produto: Produto) {
        produtoList!!.add(produto)
    }
}