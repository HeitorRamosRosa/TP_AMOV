package pt.isec.supraindustries.tp_amov.Data

data class Produto(var nome: String, var marca: String="", var quantidade : Float=0f) {
    var preco = 0f
    var unidade: Unidade? = null
    var comprado = false
    var notas: String? = null
}