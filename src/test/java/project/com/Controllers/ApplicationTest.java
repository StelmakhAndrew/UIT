package project.com.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Service.ClientService;
import project.com.Service.CrewService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    private static String testerJsonObjectClient = "{\"id\":1,\"name\":\"for Testing\",\"crewsId\":[],\"transportsId\":[]}";

    private static String jsonClient;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService mockClientService;

    @MockBean
    private CrewService mockCrewService;

    /**
     * Метод який запускається перед виконанням кожного тесту
     * Який "мокає" метод findClientById() ClientService
     */
    @Before
    public void init() {
        Client client = new Client();
        client.setName("for Testing");
        client.setId(1L);

        Crew crew = new Crew();
        crew.setId(1L);
        when(mockClientService.findClientById(1L)).thenReturn(client);
        when(mockCrewService.findCrewById(1L)).thenReturn(crew);
    }

    /**
     * @throws Exception
     * Тест для перевіпки роботи методу повернення всіх клієнтів
     */
    @Test
    public void shouldReturnAllClients() throws Exception {
        this.mockMvc.perform(get("/clients")).andExpect(status().isOk());
    }

    /**
     * @throws Exception
     * Тест для перевірки правильності повернення клієнту
     */
    @Test
    public void shouldReturnClient() throws Exception {
        this.mockMvc.perform(get("/clients/1")).andExpect(status().isOk())
                .andExpect(content().json(testerJsonObjectClient));
    }

    /**
     * @throws Exception
     * Тест для перевірки роботи пов'язування об'єктів
     */
    @Test
    public void shouldAddCrewToClient() throws Exception {
        this.mockMvc.perform(put("/clients/connectcrew?crewId=1&clientId=1")).andExpect(status().isOk());
    }

    /**
     * @throws Exception
     * Тест для перевірки виконання методів на створення та видалення об'єктів
     */
    @Test
    public void shouldCreateAndRemoveClient() throws Exception {
        this.mockMvc.perform(post("/newclient")
                .content(testerJsonObjectClient)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andDo(mvcResult -> jsonClient = mvcResult.getResponse().getContentAsString());

        long idTesterClient = Long.parseLong(jsonClient.substring(jsonClient.indexOf(":") + 1, jsonClient.indexOf(",")));
        System.out.println();
        this.mockMvc.perform(delete("/clients/{id}", idTesterClient)).andExpect(status().isOk());
    }
}
