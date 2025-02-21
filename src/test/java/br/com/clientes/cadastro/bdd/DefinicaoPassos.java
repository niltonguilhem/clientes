package br.com.clientes.cadastro.bdd;

import br.com.clientes.cadastro.controller.json.ClienteJson;
import br.com.clientes.cadastro.domain.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DefinicaoPassos {
    private Response response;

    private ClienteJson clienteJson;

    private String endpoint = "http://localhost:8080/api/v1/clientes";

    //private String dataReserva = "2024-12-11T19:00";

    @Dado("que eu tenho os dados de um cliente")
    public void queEuTenhoOsDadosDeUmCliente() {
        clienteJson = new ClienteJson("19276445854",
                "Anderson Rodrigues",
                "Rua Aura",
                "09981400"
        );
    }

    @Quando("enviar requisição para cadastrar o cliente")
    public void enviarRequisicaoParaCadastrarOCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteJson)
                .when()
                .post(endpoint);
    }

    @Entao("o cliente deve ser cadastrado com sucesso")
    public void oClienteDeveSerCadastradoComSucesso() {
        String corpoResposta = response.then().statusCode(HttpStatus.OK.value()).extract().asString();

        assertEquals("Cliente cadastrado com sucesso!", corpoResposta);
    }

    @Quando("enviar requisição para buscar clientes por nome")
    public void enviarRequisicaoParaBuscarClientesPorNome(){
        response = given().get("/api/v1/clientes/nome/{nome}", clienteJson.getNome());
    }

    @Entao("a resposta deve conter os clientes buscados por nome")
    public void aRespostaDeveConterOsClientesBuscadosPorNome(){
        List<Object> clientesRetornados = response.as(List.class);

        assertFalse(clientesRetornados.isEmpty());

        ObjectMapper objectMapper = new ObjectMapper();

        for(Object clienteObj : clientesRetornados){
            Cliente cliente = objectMapper.convertValue(clienteObj, Cliente.class);
            assertEquals(clienteJson.getNome(), cliente.getNome());
        }
    }

    @Quando("enviar requisição para buscar clientes por cpf")
    public void enviarRequisicaoParaBuscarClientesPorCpf(){
        response = given().get("/api/v1/clientes/{cpf}", clienteJson.getCpf());
    }

    @Entao("a resposta deve conter os clientes buscados por cpf")
    public void aRespostaDeveConterOsClientesBuscadosPorCpf(){
        List<Object> clientesRetornados = response.as(List.class);

        assertFalse(clientesRetornados.isEmpty());

        ObjectMapper objectMapper = new ObjectMapper();

        for(Object clienteObj : clientesRetornados){
            Cliente cliente = objectMapper.convertValue(clienteObj, Cliente.class);
            assertEquals(clienteJson.getCpf(), cliente.getCpf());
        }
    }

    @Quando("enviar requisição para buscar clientes por cep")
    public void enviarRequisicaoParaBuscarClientesPorCep(){
        response = given().get("/api/v1/clientes/cep/{cep}", clienteJson.getCep());
    }

    @Entao("a resposta deve conter os clientes buscados por cep")
    public void aRespostaDeveConterOsClientesBuscadosPorCep(){
        List<Object> clientesRetornados = response.as(List.class);

        assertFalse(clientesRetornados.isEmpty());

        ObjectMapper objectMapper = new ObjectMapper();

        for(Object clienteObj : clientesRetornados){
            Cliente cliente = objectMapper.convertValue(clienteObj, Cliente.class);
            assertEquals(clienteJson.getCep(), cliente.getCep());
        }
    }

    @Quando("enviar requisição para atualizar o cliente")
    public void enviarRequisicaoParaAtualizarOCliente(){

        clienteJson = new ClienteJson(
                clienteJson.getCpf(),
                clienteJson.getNome() + "_atualizado",
                clienteJson.getEndereco() + " atualizado",
                clienteJson.getCep() + "_atualizado"
        );

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteJson)
                .put("/api/v1/clientes/" + clienteJson.getCpf());
    }

    @Entao("a resposta deve conter o cliente atualizado")
    public void aRespostaDeveConterOClienteAtualizado(){
        assertEquals(HttpStatus.OK.value(), this.response.getStatusCode());

        assertNotNull(response.getBody());

        ClienteJson clienteJsonRetornado = response.as(ClienteJson.class);

        assertEquals(clienteJson.getCpf(), clienteJsonRetornado.getCpf());
        assertEquals(clienteJson.getNome(), clienteJsonRetornado.getNome());
        assertEquals(clienteJson.getEndereco(), clienteJsonRetornado.getEndereco());
        assertEquals(clienteJson.getCep(), clienteJsonRetornado.getCep());
    }

    @Quando("enviar requisição para remover o Cliente")
    public void enviarRequisicaoParaRemoverOCliente(){
        response = given().delete("/api/v1/clientes/" + clienteJson.getCpf());
    }

    @Entao("o cliente deve ser removido")
    public void oClienteDeveSerRemovido(){
        response.then().statusCode(HttpStatus.OK.value());
    }
}
