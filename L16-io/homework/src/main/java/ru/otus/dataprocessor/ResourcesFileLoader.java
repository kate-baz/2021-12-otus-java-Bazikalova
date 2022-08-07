package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import ru.otus.model.Measurement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String fileName;
    SimpleModule module = new SimpleModule();

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
        module.addDeserializer(Measurement.class, new FileDeserializer());
        mapper.registerModule(module);
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    @Override
    public List<Measurement> load() throws IOException, URISyntaxException {
        //читает файл, парсит и возвращает результат
        String jsonData = Files.readString(Path.of(getFileFromResource(fileName).getPath()));
        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, Measurement.class);
        return mapper.readValue(jsonData, listType);
    }


}
