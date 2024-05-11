package com.empresa.hito_2_3t_programacion_fx.DTO;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class DataDTOTypeAdapter extends TypeAdapter<DataDTO> {

    @Override
    public void write(JsonWriter out, DataDTO value) throws IOException {
        out.beginObject();
        out.name("id").value(value.getId());
        out.name("name").value(value.getName());
        out.name("description").value(value.getDescription());
        out.name("category").value(value.getCategory());
        out.name("price").value(value.getPrice().toString());
        out.endObject();
    }

    @Override
    public DataDTO read(JsonReader in) throws IOException {
        return null;
    }
}
