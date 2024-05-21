package com.empresa.hito_2_3t_programacion_fx.HTTP.Request;

import com.empresa.hito_2_3t_programacion_fx.DTO.DataDTO;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class PostRequest {
    private static final Logger LOGGER = Logger.getLogger(PostRequest.class.getName());

    // Método para enviar solicitud POST
    public void sendPostRequest(DataDTO dataDTO) {
        try {
            // Crea un objeto Gson con un adaptador de tipo personalizado para DataDTO
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(DataDTO.class, new DataDTOTypeAdapter())
                    .create();

            // Convierte el objeto DataDTO a JSON usando Gson
            String json = gson.toJson(dataDTO);

            // Crea el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crea la solicitud POST con el JSON generado
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8091/api/products/create")) // Endpoint de la API
                    .header("Content-Type", "application/json") // Tipo de contenido
                    .POST(HttpRequest.BodyPublishers.ofString(json)) // Cuerpo del POST con el JSON
                    .build();

            // Envía la solicitud y recibe la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Registra el código de estado y la respuesta de la API
            LOGGER.info("Código de estado: " + response.statusCode());
            LOGGER.info("Respuesta de la API: " + response.body());

        } catch (Exception e) {
            LOGGER.severe("Error al enviar solicitud POST: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Adaptador de tipo personalizado para DataDTO
    private static class DataDTOTypeAdapter implements JsonSerializer<DataDTO> {
        @Override
        public JsonElement serialize(DataDTO dataDTO, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", dataDTO.getName());
            jsonObject.addProperty("description", dataDTO.getDescription());
            jsonObject.addProperty("category", dataDTO.getCategory());
            jsonObject.addProperty("price", dataDTO.getPrice().toString());
            return jsonObject;
        }
    }
}