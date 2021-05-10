package br.com.cristian.apigerenciamentoprodutos.Service;

import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import br.com.cristian.apigerenciamentoprodutos.Respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;


    public List<Product> listarProdutos(String produtcs){
        if(produtcs != null){
            return productRepository.listarProdutos(produtcs);
        }
        return productRepository.listarProdutos();
    }

    public Integer add(Product product){
        if(product.getId()==null){
            product.setId(productRepository.count() +1);
        }
        productRepository.adicionaItemNaLista(product);
        return product.getId();
    }

    public Product findById(Integer id){
       return productRepository.findById(id);
    }

    public void update(Product product){
        productRepository.update(product);
    }

    public void delete(Integer id){
        productRepository.delete(id);
    }

    public int test(int soma1, int soma2){
        return soma1+soma2;
    }

}
