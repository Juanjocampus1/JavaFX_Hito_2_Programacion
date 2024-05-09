package com.empresa.hito_2_3t_programacion_fx.HTTP.Request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.empresa.hito_2_3t_programacion_fx.DTO.DataDTO;
import com.google.gson.Gson;


import java.util.logging.Logger;

public class PostRequest {
    private static final Logger LOGGER = Logger.getLogger(PostRequest.class.getName());

    // Método para enviar solicitud POST
    public void sendPostRequest(DataDTO dataDTO) {

        try {
            // Convierte el objeto DataDTO a JSON usando Gson
            Gson gson = new Gson();
            String json = gson.toJson(dataDTO);

            // Crea el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crea la solicitud POST con el JSON generado
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/api/products/create")) // Endpoint de la API
                    .header("Content-Type", "application/json") // Tipo de contenido
                    .POST(HttpRequest.BodyPublishers.ofString(json)) // Cuerpo del POST con el JSON
                    .build();

            // Envía la solicitud y recibe la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Registra el código de estado y la respuesta de la API
            LOGGER.info("Código de estado: " + response.statusCode());
            LOGGER.info("Respuesta de la API: " + response.body());

        }
        catch (Exception e) {
            LOGGER.severe("Error al enviar solicitud POST: " + e.getMessage());
            e.printStackTrace();
        }
    }
}