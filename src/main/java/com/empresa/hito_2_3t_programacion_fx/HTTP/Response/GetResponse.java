package com.empresa.hito_2_3t_programacion_fx.HTTP.Response;

import com.empresa.hito_2_3t_programacion_fx.DTO.DataDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GetResponse {
    private static final Logger LOGGER = Logger.getLogger(GetResponse.class.getName());

    // Método para realizar una solicitud GET
    public List<DataDTO> sendGetRequest() {
        List<DataDTO> dataList = new ArrayList<>();
        try {
            // Crea el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crea la solicitud GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8091/api/products/findAll")) // Endpoint para la solicitud GET
                    .GET() // Define que es una solicitud GET
                    .build();

            // Envía la solicitud y recibe la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Muestra el código de estado y el cuerpo de la respuesta
            LOGGER.info("Código de estado: " + response.statusCode());
            LOGGER.info("Respuesta de la API: " + response.body());

            // Parsea la respuesta JSON
            JSONArray jsonArray = new JSONArray(response.body());

            // Convierte cada objeto JSON en un objeto DataDTO y lo añade a la lista
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DataDTO dataDTO = new DataDTO(
                        jsonObject.getLong("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("description"),
                        jsonObject.getString("category"),
                        jsonObject.getBigDecimal("price")
                );
                dataList.add(dataDTO);
            }

        } catch (Exception e) {
            LOGGER.severe("Error al enviar solicitud GET: " + e.getMessage());
            e.printStackTrace();
        }
        return dataList;
    }
}