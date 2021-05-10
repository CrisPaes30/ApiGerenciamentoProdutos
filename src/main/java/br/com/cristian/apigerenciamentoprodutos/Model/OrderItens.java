package br.com.cristian.apigerenciamentoprodutos.Model;

public class OrderItens {

    String nome;
    Integer quantidade;
    Double desconto;
    Integer codigo;

    public OrderItens(String nome, Integer quantidade, Double desconto, Integer codigo) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.codigo= codigo;
    }

    public OrderItens(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}

