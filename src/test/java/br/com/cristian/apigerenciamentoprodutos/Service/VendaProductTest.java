package br.com.cristian.apigerenciamentoprodutos.Service;


import br.com.cristian.apigerenciamentoprodutos.Model.OrderItens;
import br.com.cristian.apigerenciamentoprodutos.Model.VendaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class VendaProductTest {


    @Autowired
    VendaService vendaService;

    @MockBean
    VendaResponse vendaResponse;


    @Test
    public void testaCalculoDeVenda(){

        when(vendaResponse.getValorTotal()).thenReturn(4.0);

        VendaResponse vR = vendaService.calculo(listaProdutos());


        Assertions.assertEquals(4.0, vR.getValorTotal());

       // verify(vendaResponse, times(0)).getValorTotal();
    }

    private static List<OrderItens> listaProdutos() {
        List<OrderItens> prod = new ArrayList<>();
        prod.add(new OrderItens( null, 1, 1.0,
                2));
        return prod;
    }
}