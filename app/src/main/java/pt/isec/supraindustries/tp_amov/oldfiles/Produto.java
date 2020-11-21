package pt.isec.supraindustries.tp_amov.oldfiles;

import pt.isec.supraindustries.tp_amov.Data.Unidade;

public class Produto {
    String nome;
    String marca;
    float preco;
    int quantidade;
    Unidade unidade;
    boolean comprado;
    String notas;



    public Produto(String nome, String marca, int quantidade) {
        this.nome = nome;
        this.marca = marca;
    }
}
