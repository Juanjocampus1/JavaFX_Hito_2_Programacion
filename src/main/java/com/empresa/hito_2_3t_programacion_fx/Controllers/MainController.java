package com.empresa.hito_2_3t_programacion_fx.Controllers;

import com.empresa.hito_2_3t_programacion_fx.DTO.DataDTO;
import com.empresa.hito_2_3t_programacion_fx.HTTP.Request.DeleteRequest;
import com.empresa.hito_2_3t_programacion_fx.HTTP.Request.PostRequest;
import com.empresa.hito_2_3t_programacion_fx.HTTP.Request.PutRequest;
import com.empresa.hito_2_3t_programacion_fx.HTTP.Response.GetResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class MainController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<DataDTO> dataTable;

    @FXML
    private TableColumn<DataDTO, Long> idColumn;

    @FXML
    private TableColumn<DataDTO, String> nameColumn;

    @FXML
    private TableColumn<DataDTO, String> descriptionColumn;

    @FXML
    private TableColumn<DataDTO, String> categoryColumn;

    @FXML
    private TableColumn<DataDTO, BigDecimal> priceColumn;

    public void initialize() {
        // Configurar ancho relativo para las columnas
        idColumn.prefWidthProperty().bind(dataTable.widthProperty().multiply(0.1)); // 10% del ancho total
        nameColumn.prefWidthProperty().bind(dataTable.widthProperty().multiply(0.2)); // 20% del ancho total
        descriptionColumn.prefWidthProperty().bind(dataTable.widthProperty().multiply(0.3)); // 30% del ancho total
        categoryColumn.prefWidthProperty().bind(dataTable.widthProperty().multiply(0.2)); // 20% del ancho total
        priceColumn.prefWidthProperty().bind(dataTable.widthProperty().multiply(0.2)); // 20% del ancho total

        // Configurar las columnas para mostrar los datos correctos
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Crear un objeto GetResponse y enviar la solicitud GET a la API
        GetResponse getResponse = new GetResponse();
        List<DataDTO> dataList = getResponse.sendGetRequest();

        // Mapear los datos recibidos en la tabla
        ObservableList<DataDTO> observableList = FXCollections.observableArrayList(dataList);
        dataTable.setItems(observableList);

        // Establecer el RowFactory personalizado
        dataTable.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(DataDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setStyle("");
                } else {
                    if (isSelected()) {
                        setStyle("-fx-text-fill: white;");
                    } else {
                        setStyle("-fx-text-fill: #000;");
                    }
                }
            }
        });

        // Agregar un manejador de eventos de clic a la tabla
        dataTable.setOnMouseClicked(event -> {
            if (!dataTable.getSelectionModel().isEmpty()) {
                DataDTO selectedData = dataTable.getSelectionModel().getSelectedItem();
                idField.setText(String.valueOf(selectedData.getId()));
                nameField.setText(selectedData.getName());
                descriptionField.setText(selectedData.getDescription());
                categoryField.setText(selectedData.getCategory());
                priceField.setText(selectedData.getPrice().toString());
            }
        });
    }

    @FXML
    protected void onCreateButtonClick() {
        // Extraer los valores de los campos de texto
        String name = nameField.getText();
        String description = descriptionField.getText();
        String category = categoryField.getText();
        String priceText = priceField.getText();

        //definir una constante con el valor error
        final String ERROR = "ERROR";

        // Verificar si algún campo está vacío
        if (name.isEmpty() || description.isEmpty() || category.isEmpty() || priceText.isEmpty()) {
            showAlert(ERROR, "Todos los campos deben estar llenos.");
            return;
        }

        // Verificar si el precio es un número
        if (!priceText.matches("\\d+(\\.\\d+)?")) {
            showAlert(ERROR, "El precio debe ser un número.");
            return;
        }

        // Verificar si el precio es negativo
        BigDecimal price = new BigDecimal(priceText);
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            showAlert(ERROR, "El precio no puede ser negativo.");
            return;
        }

        // Crear un objeto DataDTO con los valores extraídos
        DataDTO dataDTO = new DataDTO(null, name, description, category, price);

        // Crear un objeto PostRequest y enviar la solicitud POST a la API
        PostRequest postRequest = new PostRequest();
        postRequest.sendPostRequest(dataDTO);

        // Limpiar los campos de texto después de enviar la solicitud
        nameField.clear();
        descriptionField.clear();
        categoryField.clear();
        priceField.clear();

        // Crear un objeto GetResponse y enviar la solicitud GET a la API
        GetResponse getResponse = new GetResponse();
        List<DataDTO> dataList = getResponse.sendGetRequest();

        // Mapear los datos recibidos en la tabla
        ObservableList<DataDTO> observableList = FXCollections.observableArrayList(dataList);
        dataTable.setItems(observableList);
    }

    @FXML
    protected void onUpdateButtonClick() {
        // Extraer los valores de los campos de texto
        String idText = idField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();
        String category = categoryField.getText();
        String priceText = priceField.getText();

        final String ERROR = "ERROR";

        // Verificar si algún campo está vacío
        if (idText.isEmpty() || name.isEmpty() || description.isEmpty() || category.isEmpty() || priceText.isEmpty()) {
            showAlert(ERROR, "Todos los campos deben estar llenos.");
            return;
        }

        // Verificar si el precio es un número
        if (!priceText.matches("\\d+(\\.\\d+)?")) {
            showAlert(ERROR, "El precio debe ser un número.");
            return;
        }

        // Verificar si el precio es negativo
        BigDecimal price = new BigDecimal(priceText);
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            showAlert(ERROR, "El precio no puede ser negativo.");
            return;
        }

        // Crear un objeto DataDTO con los valores extraídos
        long id = Long.parseLong(idText);
        DataDTO dataDTO = new DataDTO(id, name, description, category, price);

        // Crear un objeto PutRequest y enviar la solicitud PUT a la API
        PutRequest putRequest = new PutRequest();
        putRequest.sendPutRequest(dataDTO, id);

        // Limpiar los campos de texto después de enviar la solicitud
        idField.clear();
        nameField.clear();
        descriptionField.clear();
        categoryField.clear();
        priceField.clear();

        // Crear un objeto GetResponse y enviar la solicitud GET a la API
        GetResponse getResponse = new GetResponse();
        List<DataDTO> dataList = getResponse.sendGetRequest();

        // Mapear los datos recibidos en la tabla
        ObservableList<DataDTO> observableList = FXCollections.observableArrayList(dataList);
        dataTable.setItems(observableList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void onDeleteButtonClick() {
        // Extraer el valor del campo de texto ID
        Long id = Long.parseLong(idField.getText());

        // Crear un objeto DataDTO con el ID extraído
        DataDTO dataDTO = new DataDTO();
        dataDTO.setId(id);

        // Crear un objeto DeleteRequest y enviar la solicitud DELETE a la API
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.sendDeleteRequest(dataDTO);

        // Limpiar el campo de texto ID después de enviar la solicitud
        idField.clear();
        nameField.clear();
        descriptionField.clear();
        categoryField.clear();
        priceField.clear();

        // Crear un objeto GetResponse y enviar la solicitud GET a la API
        GetResponse getResponse = new GetResponse();
        List<DataDTO> dataList = getResponse.sendGetRequest();

        // Mapear los datos recibidos en la tabla
        ObservableList<DataDTO> observableList = FXCollections.observableArrayList(dataList);
        dataTable.setItems(observableList);
    }

    @FXML
    protected void onClearButtonClick() {
        // Limpiar todos los campos de texto
        idField.clear();
        nameField.clear();
        descriptionField.clear();
        categoryField.clear();
        priceField.clear();
    }
}