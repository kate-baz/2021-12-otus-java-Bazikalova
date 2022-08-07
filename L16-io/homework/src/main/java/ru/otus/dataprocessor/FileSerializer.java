package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class FileSerializer implements Serializer {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String filePath;

    public FileSerializer(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        //формирует результирующий json и сохраняет его в файл
        var file = new File(filePath);
        mapper.writeValue(file, data);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        var loader = new ResourcesFileLoader("inputData.json");
        var processor = new ProcessorAggregator();
        var serializer = new FileSerializer("newJson.json");


        List<Measurement> dataAsList = loader.load();
        Map<String, Double> dataAsMap = processor.process(dataAsList);
        serializer.serialize(dataAsMap);
    }
}
