package pt.isec.supraindustries.tp_amov.oldfiles;

import java.util.List;

import pt.isec.supraindustries.tp_amov.Data.Produto;

public class Lista {
    String nome;
    List<pt.isec.supraindustries.tp_amov.Data.Produto> produtoList;

    public Lista(String nome) {
        this.nome = nome;
    }

    public void addProduto(Produto produto){
        produtoList.add(produto);
    }
}
