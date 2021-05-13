package br.com.cristian.apigerenciamentoprodutos.Service;

import br.com.cristian.apigerenciamentoprodutos.Model.OrderItens;
import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import br.com.cristian.apigerenciamentoprodutos.Model.VendaResponse;
import br.com.cristian.apigerenciamentoprodutos.Respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {


    @Autowired
    private ProductService productService;


    public VendaResponse calculo(List<OrderItens>ordensList){

        List<OrderItens> listaOrdens = new ArrayList<>();

        double valorTotal = 0;

        for(OrderItens item: ordensList){
            Product pd = productService.findById(item.getCodigo());
            double valorProduto = calculoDesconto(pd.getValorProd(),item.getQuantidade(),
                    item.getDesconto());
            valorTotal += valorProduto;
            item.setNome(pd.getNomeProd());
            listaOrdens.add(item);

        }

        return new VendaResponse(valorTotal,listaOrdens);
    }


    public double calculoDesconto(double valorProduto, int quantidade, double desconto){
        if(desconto > 0){
            return (valorProduto * quantidade) - desconto;
        }
        return valorProduto * quantidade;
    }

}
