package br.com.cristian.apigerenciamentoprodutos.Service;

import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import br.com.cristian.apigerenciamentoprodutos.Respository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

//@SpringBootTest
public class ProdutoServiceTest {

//    @Autowired
//    ProductRepository productRepository;

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductService productService= mock(ProductService.class);
    private Product product = mock(Product.class);



    @Test
    public void testaListaProdutosTemConteudoOuEstaVazia(){
        ProductService productService = new ProductService();
        List<Product> resultado = productRepository.listarProdutos();
        assertNotEquals(null, resultado);

    }

    public static List<Product> listaDeProdutos() {
        List<Product> prod = new ArrayList<Product>();
        prod.add(new Product(10, "Controle", 5, 15.00,
                10));
        prod.add(new Product(11, "Carrinho Hot Weels", 20, 5.0,
                9));
        return prod;
    }

    @Test
    public void testaAddItemALista(){
        ProductService productService = new ProductService();

        Product product = new Product(null,"Test",5,10.00,1);

        when(productRepository.count()).thenReturn(9);
        doNothing().when(productRepository).adicionaItemNaLista(Matchers.any(Product.class));

        int prod = productService.add(product);

        Assertions.assertEquals(prod,9);

        verify(productRepository,times(1)).adicionaItemNaLista(product);


    }










}
