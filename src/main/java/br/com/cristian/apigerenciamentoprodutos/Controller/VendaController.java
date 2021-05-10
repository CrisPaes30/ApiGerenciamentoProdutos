package br.com.cristian.apigerenciamentoprodutos.Controller;

import br.com.cristian.apigerenciamentoprodutos.Model.OrderItens;
import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import br.com.cristian.apigerenciamentoprodutos.Model.VendaResponse;
import br.com.cristian.apigerenciamentoprodutos.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final List<Product> products;

    @Autowired
    private VendaService vendaService;


    public VendaController(List<Product> products) {
        this.products = new ArrayList<>();
    }

    @PostMapping
    public ResponseEntity<VendaResponse> getCalc(@RequestBody List<OrderItens> listOrder){
        VendaResponse vendaResponse = vendaService.calculo(listOrder);
        return ResponseEntity.ok().body(vendaResponse);
    }

    @GetMapping("/{nomeProd}")
    public ResponseEntity<VendaResponse> getBuyFilter(@PathVariable("nomeProd") List<OrderItens> listOrder){
        VendaResponse prod = vendaService.calculo(listOrder);
        return ResponseEntity.ok().body(prod);
    }
}
