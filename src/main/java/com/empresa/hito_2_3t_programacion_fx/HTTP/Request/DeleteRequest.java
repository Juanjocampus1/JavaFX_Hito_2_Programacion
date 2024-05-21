package com.empresa.hito_2_3t_programacion_fx.HTTP.Request;

import com.empresa.hito_2_3t_programacion_fx.DTO.DataDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class DeleteRequest {
    private static final Logger LOGGER = Logger.getLogger(DeleteRequest.class.getName());

    public void sendDeleteRequest(DataDTO dataDTO) {
        try {
            // Extraer el ID del objeto DataDTO
            Long id = dataDTO.getId();

            // Crear el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear la solicitud DELETE con el ID del recurso
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8091/api/products/delete/" + id)) // Endpoint con el ID
                    .DELETE() // Solicitud DELETE
                    .build();

            // Enviar la solicitud y manejar la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Registrar el resultado
            LOGGER.info("Código de estado: " + response.statusCode());
            LOGGER.info("Respuesta de la API: " + response.body());

            if (response.statusCode() != 200) {
                // Si el estado no es 200, algo salió mal
                LOGGER.severe("Error al eliminar el recurso: " + response.body());
            }

        } catch (Exception e) {
            // Manejo de excepciones
            LOGGER.severe("Error al enviar solicitud DELETE: " + e.getMessage());
            e.printStackTrace();
        }
    }
}