package com.empresa.hito_2_3t_programacion_fx.HTTP.Request;

import com.empresa.hito_2_3t_programacion_fx.DTO.DataDTO;
import com.empresa.hito_2_3t_programacion_fx.DTO.DataDTOTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class PutRequest {
    private static final Logger LOGGER = Logger.getLogger(PutRequest.class.getName());

    // Método para enviar una solicitud PUT
    public void sendPutRequest(DataDTO dataDTO, long id) {
        try {
            // Convertir el objeto a JSON
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(DataDTO.class, new DataDTOTypeAdapter())
                    .create();
            String json = gson.toJson(dataDTO);

            HttpClient client = HttpClient.newHttpClient();

            // Crear la solicitud PUT con el JSON
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8091/api/products/update/" + id)) // URI con ID
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            LOGGER.info("Código de estado: " + response.statusCode());
            LOGGER.info("Respuesta de la API: " + response.body());

        } catch (Exception e) {
            LOGGER.severe("Error al enviar solicitud PUT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}