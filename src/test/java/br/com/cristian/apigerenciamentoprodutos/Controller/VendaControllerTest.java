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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VendaControllerTest {

    private static final String MOCKED_RESULT =
            "[{\n" +
                    " \"id\": 2,\n" +
                    " \"quantidade\": 2,\n" +
                    "  \"desconto\": 2.0,\n" +
                    "\"codigo\": 5\n" +
                    " }]";


    private static WireMockServer wireMockServer = new  WireMockServer(options().port(8081));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void testeSeVederUmProdudoRetornaStatusCode200() throws Exception {
        wireMockServer.stubFor(
                WireMock.post(WireMock.urlPathEqualTo("/venda"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type","application/json")
                                .withBody((MOCKED_RESULT))));


        mockMvc.perform(MockMvcRequestBuilders.post("/venda")
                .content(MOCKED_RESULT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }




}
