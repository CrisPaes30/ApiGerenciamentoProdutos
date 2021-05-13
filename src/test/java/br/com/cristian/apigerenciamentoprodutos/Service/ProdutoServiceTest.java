package br.com.cristian.apigerenciamentoprodutos.Service;

import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import br.com.cristian.apigerenciamentoprodutos.Respository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ProdutoServiceTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @MockBean
    Product product;


    @Test
    public void testaListaProdutosTemConteudoOuEstaVazia(){
        List<Product> resultado = productRepository.listarProdutos();
        assertNotEquals(null, resultado);
    }

    @Test
    public void testaListagemDeProdutos(){
        List<Product> lista = productService.listaDeProdutos(null);

        Assertions.assertTrue(EhLista(lista), String.valueOf(true));

        verify(productRepository, times(1)).listarProdutos();
    }

    private Boolean EhLista(List<Product> lista){
        if (lista != null){
            return true;
        }
        return false;
    }


    @Test
    public void testaAddItemALista(){

        Product product = new Product(null,"Test",5,10.00,1);

        when(productRepository.count()).thenReturn(10);
        doNothing().when(productRepository).adicionaItemNaLista(null);

        int prod = productService.add(product);

        assertEquals(10,prod);

        verify(productRepository,times(1)).adicionaItemNaLista(product);
        verify(productRepository, times(1)).count();

    }

    @Test
    public void testaAddItemAListaComId(){

        Product product = new Product(10,"Test",5,10.00,1);

        when(productRepository.count()).thenReturn(10);
        doNothing().when(productRepository).adicionaItemNaLista(null);

        int prod = productService.add(product);

        assertEquals(prod,10);

        verify(productRepository,times(1)).adicionaItemNaLista(product);
        verify(productRepository, times(0)).count();

    }

    @Test
    public void testaFindById(){
        when(productRepository.findById(1)).thenReturn(product);

        Product buscaId = productService.findById(1);

        Assertions.assertEquals(product.getId(), buscaId.getId());

        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void testaUpdate(){

        Product prod = new Product(1,"Test",5,10.00,1);

        doNothing().when(productRepository).update(product);

        productService.update(prod);

        verify(productRepository, times(1)).update(prod);

    }

    @Test
    public void testaDelete(){
        doNothing().when(productRepository).delete(2);

        productService.delete(2);

        verify(productRepository, times(1)).delete(2);

    }






}
