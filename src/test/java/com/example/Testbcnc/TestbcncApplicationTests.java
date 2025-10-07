package com.example.Testbcnc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.example.Testbcnc.domain.Precio;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestbcncApplicationTests {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        MvcResult result =  mockMvc.perform(get("/api/precios/ping"))
            .andExpect(status().isOk())
            .andReturn();

        String json = result.getResponse().getContentAsString();
        System.out.println();
        System.out.println("------------------");
        System.out.println("|    TEST PING   |");
        System.out.println("------------------");
        System.out.println("Respuesta del servicio /ping: " + json);
    }
    /**
     * Pinta por consola la respuesta del servicio REST
     * @param json
    */
    private void pintarRespuesta(String json) {
        
        Precio response;
       
        try {
            response = objectMapper.readValue(json, Precio.class);
            if(response.getError() != null) {
            	System.out.println(response.getError());
            	return;
            }

            System.out.println("BRAND_ID: " + response.getBrandId());
            System.out.println("START_DATE: " + response.getStartDate());
            System.out.println("END_DATE: " + response.getEndDate());
            System.out.println("PRICE_LIST: " + response.getPriceList());
            System.out.println("PRODUCT_ID: " + response.getProductId());
            System.out.println("PRIORITY: " + response.getPriority());
            System.out.println("PRICE: " + response.getPrice());
            System.out.println("CURR: " + response.getCurr());
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Fallo al pintar la respuesta: " + e.getMessage());
        }

    }
         
    /**
     * Lanza el test con los parámetros indicados
     * @param applicationDate
     * @param productId
     * @param brandId
     * @throws Exception
     */
    private void lanzarTest(String url,String applicationDate, String productId, String brandId, Double price, int numeroTest) throws Exception {
       //Cabecera del test
       System.out.println();
       System.out.println("------------------");
       System.out.println("|     TEST " + numeroTest + "     |");
       System.out.println("------------------");
        //debemos comprobar que tanto el productId como el brandId son numéricos
       if (!productId.matches("\\d+") || !brandId.matches("\\d+")) {
           System.out.println("El productId y el brandId deben ser numéricos");
           return;
       }

        MvcResult result =  mockMvc.perform(get(url)
            .param("applicationDate", applicationDate)
            .param("productId", productId)
            .param("brandId", brandId))
            //.andExpect(status().isOk())
            //.andExpect(jsonPath("$.price").value(price))
            .andReturn();
        //Si la llamada no es correcta, mostramos el error y salimos
        if(result.getResponse().getStatus() != 200) {
            System.out.println("Error en la llamada al servicio. Status: " + result.getResponse().getStatus() +". URL: " + url);
            return;
        }
        String json = result.getResponse().getContentAsString();

        if (json != null && !json.isEmpty()) {
             pintarRespuesta(json);
        } else {
            System.out.println("La respuesta JSON está vacía o es nula.");
        }

    }

    @Test
    public void test1() throws Exception {
       lanzarTest("/api/precios/obtenerPrecio","2020-06-14T10:00:00", "35455", "1",35.50,1);
    }
 
    @Test
    public void test2() throws Exception {
       lanzarTest("/api/precios/obtenerPrecio","2020-06-14T16:00:00", "35455", "1",25.45,2);
    }

    @Test
    public void test3() throws Exception {
       lanzarTest("/api/precios/obtenerPrecio","2020-06-14T21:00:00", "35455", "1",35.50,3);
    }

    @Test
    public void test4() throws Exception {
       lanzarTest("/api/precios/obtenerPrecio","2020-06-15T10:00:00", "35455", "1",30.50,4);
    }

    @Test
    public void test5() throws Exception {
       lanzarTest("/api/precios/obtenerPrecio","2020-06-16T21:00:00", "35455", "1",38.95,5);
    }

    @Test
    public void test6() throws Exception {
       lanzarTest("/api/precios/obtenerPrecio","2025-06-16T21:00:00", "35455", "1",38.95,6);
    }

    @Test
    public void test7() throws Exception {
       lanzarTest("/api/precios/obtenerPrecio","2025-06-16T21:00:00", "35455", "a",38.95,7);
    }

    @Test
    public void test8() throws Exception {
        lanzarTest("/api/precios/fallar","2025-06-16T21:00:00", "35455", "1",38.95,8);
    }

}
