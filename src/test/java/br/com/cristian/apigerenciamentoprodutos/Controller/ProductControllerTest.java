package br.com.cristian.apigerenciamentoprodutos.Controller;


import br.com.cristian.apigerenciamentoprodutos.Model.Product;
import br.com.cristian.apigerenciamentoprodutos.Respository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    private static final String MOCKED_RESULT =
            " {\n" +
                    " \"id\": 1,\n" +
                    " \"nomeProd\": \"Test\",\n" +
                    "  \"quantProd\": 5,\n" +
                    "  \"valorProd\": 15.0,\n" +
                    "\"codigoProd\": 10\n" +
                    " }";


    private static WireMockServer wireMockServer = new  WireMockServer(options().port(8081));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;



    @BeforeAll
    static void beforeAll() throws JsonProcessingException {
        wireMockServer.start();
    }

    @BeforeEach
    void setup(){
        wireMockServer.resetAll();
    }

    @AfterAll
    static  void afterAll(){
        wireMockServer.stop();
    }

    @Test
    public void testeFindAllProdutos() throws Exception{
        wireMockServer.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("/produtos"))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type","application/json")
                .withBody(String.valueOf((productRepository)))));

        mockMvc.perform(MockMvcRequestBuilders.get("/produtos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)));

    }

    @Test
    public void testeSeAdicionarUmProdudoRetornaStatusCode201() throws Exception {
        wireMockServer.stubFor(
                WireMock.post(WireMock.urlPathEqualTo("/produtos"))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type","application/json")));
                                //.withBody(((MOCKED_RESULT)))));

        Product product = new Product(15,"Test",5,10.00,1);

        mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
                //.andExpect(jsonPath("$", is(9)));

    }

    @Test
    public void testeAtualizaUmProdudoRetornaStatusCode204() throws Exception {
        wireMockServer.stubFor(
                WireMock.put(WireMock.urlPathEqualTo("/produtos"))
                        .willReturn(aResponse()
                                .withStatus(204)
                                .withHeader("Content-Type","application/json")
                                .withBody(((MOCKED_RESULT)))));

        mockMvc.perform(MockMvcRequestBuilders.put("/produtos")
                .content(MOCKED_RESULT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testeSeAoDeletarUmProdutoRetornaStatusCode204() throws Exception{
        wireMockServer.stubFor(
                WireMock.delete(WireMock.urlPathEqualTo("/produtos/id"))
                        .willReturn(aResponse()
                                .withStatus(204)
                                .withHeader("Content-Type","application/json")
                                .withBody(MOCKED_RESULT)));


        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }


}
