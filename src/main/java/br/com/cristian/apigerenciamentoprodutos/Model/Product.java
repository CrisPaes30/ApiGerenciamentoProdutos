package br.com.cristian.apigerenciamentoprodutos.Model;

import javax.persistence.Id;

public class Product {

    @Id
    Integer id;

    String nomeProd;
    Integer quantProd;
    Double valorProd;
    Integer codigoProd;
    Double descontoMin;



    public Product(Integer id, String nomeProd, Integer quantProd,
                   Double valorProd, Integer codigoProd) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.quantProd = quantProd;
        this.valorProd = valorProd;
        this.codigoProd = codigoProd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public Integer getQuantProd() {
        return quantProd;
    }

    public void setQuantProd(Integer quantProd) {
        this.quantProd = quantProd;
    }

    public Double getValorProd() {
        return valorProd;
    }

    public void setValorProd(Double valorProd) {
        this.valorProd = valorProd;
    }

    public Integer getCodigoProd() {
        return codigoProd;
    }

    public void setCodigoProd(Integer codigoProd) {
        this.codigoProd = codigoProd;
    }


    public void setDescontoMin(Double desconto) {
        this.descontoMin = desconto;
    }
}
