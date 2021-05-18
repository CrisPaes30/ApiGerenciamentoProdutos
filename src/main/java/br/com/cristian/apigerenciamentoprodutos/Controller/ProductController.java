package br.com.cristian.apigerenciamentoprodutos.Controller;

import br.com.cristian.apigerenciamentoprodutos.Model.OrderItens;
import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import br.com.cristian.apigerenciamentoprodutos.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final List<Product> products;

    @Autowired
    private ProductService productService;


    public ProductController(List<Product> products) {
        this.products = new ArrayList<>();
    }

    @GetMapping
    public List<Product> findAll(@RequestParam(required = false) String product) {
        return productService.listaDeProdutos(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Integer id){
        return productService.findById(id);

    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Product product) {
        productService.add(product);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Product product) {
        productService.update(product);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
