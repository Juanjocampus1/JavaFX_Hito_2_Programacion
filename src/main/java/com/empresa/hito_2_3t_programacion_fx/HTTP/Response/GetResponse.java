package com.empresa.hito_2_3t_programacion_fx.HTTP.Response;

import org.json.JSONArray;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class GetResponse {
    private static final Logger LOGGER = Logger.getLogger(GetResponse.class.getName());

    // Interfaz para el listener
    public interface OnDataReceivedListener {
        void onDataReceived(JSONArray data);
    }

    // Variable para almacenar el listener
    private OnDataReceivedListener listener;

    // Método para establecer el listener
    public void setOnDataReceivedListener(OnDataReceivedListener listener) {
        this.listener = listener;
    }

    // Método para realizar una solicitud GET
    public void sendGetRequest() {
        try {
            // Crea el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crea la solicitud GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/api/products/findAll")) // Endpoint para la solicitud GET
                    .GET() // Define que es una solicitud GET
                    .build();

            // Envía la solicitud y recibe la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Muestra el código de estado y el cuerpo de la respuesta
            LOGGER.info("Código de estado: " + response.statusCode());
            LOGGER.info("Respuesta de la API: " + response.body());

            // Parsea la respuesta JSON
            JSONArray jsonArray = new JSONArray(response.body());

            // Verifica si el listener está establecido y notifica los datos recibidos
            if (listener != null) {
                listener.onDataReceived(jsonArray);
            }

        } catch (Exception e) {
            LOGGER.severe("Error al enviar solicitud GET: " + e.getMessage());
            e.printStackTrace();
        }
    }
}