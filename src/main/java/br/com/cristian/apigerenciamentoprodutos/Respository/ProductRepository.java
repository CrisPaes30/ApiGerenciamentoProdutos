package br.com.cristian.apigerenciamentoprodutos.Respository;

import br.com.cristian.apigerenciamentoprodutos.Model.OrderItens;
import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private  final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = listaDeProdutos();
    }



    public static List<Product> listaDeProdutos() {
         List<Product> prod  = new ArrayList<Product>();
               prod.add( new Product(1, "Controle", 5, 15.00,
                        10));
        prod.add(new Product(2, "Carrinho Hot Weels", 20, 5.0,
                        9));
        prod.add(new Product(3, "Caneca", 40, 10.00,
                        8));
        prod.add(new Product(4, "Jogo de Toalha", 10, 50.00,
                        15));
        prod.add(new Product(5, "Playstation 5", 5, 5.0,
                        5));
        prod.add(new Product(6, "xBox SeriesX", 8, 8.0,
                        4));
        prod.add(new Product(7, "Caixa de Caneta", 2, 10.0,
                        11));
        prod.add(new Product(8, "Estojo ", 15, 5.0,
                        2));

        return prod;
    }

    public List<Product>listarProdutos(){
        return products;
    }


    public List<Product>listarProdutos(String product) {
        return products.stream().filter(pdt -> pdt.getNomeProd().contains(product))
                .filter(pdt -> pdt.getValorProd()>0).collect(Collectors.toList());

    }

    public int count(){
        return products.size();
    }

    public void adicionaItemNaLista(Product product){
        products.add(product);
    }

    public Product findById(Integer id){
       return products.stream().filter(pdt -> pdt.getId().equals(id))
               .findFirst()
               .orElse(null);
    }

    public void update(Product product){
        products.stream().filter(pdt -> pdt.getId().equals(product.getId()))
                .forEach(pdt-> pdt.setNomeProd(product.getNomeProd()));
        products.stream().filter(pdt -> pdt.getId().equals(product.getId()))
                .forEach(pdt-> pdt.setQuantProd(product.getQuantProd()));
        products.stream().filter(pdt -> pdt.getId().equals(product.getId()))
                .forEach(pdt-> pdt.setValorProd(product.getValorProd()));

    }

    public void delete (Integer id){
        products.removeIf(pdt -> pdt.getId().equals(id));
    }


}
