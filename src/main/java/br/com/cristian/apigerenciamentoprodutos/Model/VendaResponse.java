package br.com.cristian.apigerenciamentoprodutos.Model;

import java.util.ArrayList;
import java.util.List;

public class VendaResponse {

    Double valorTotal;
    List<OrderItens> listaprodutos = new ArrayList<>();

    public VendaResponse(Double valorTotal, List<OrderItens> listaprodutos) {
        this.valorTotal = valorTotal;
        this.listaprodutos = listaprodutos;
    }



    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<OrderItens> getListaprodutos() {
        return listaprodutos;
    }

    public void setListaprodutos(List<OrderItens> listaprodutos) {
        this.listaprodutos = listaprodutos;
    }
}
