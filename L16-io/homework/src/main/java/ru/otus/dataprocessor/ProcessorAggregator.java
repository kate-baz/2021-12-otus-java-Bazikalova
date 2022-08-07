package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.*;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        Map<String, Double> map = new TreeMap<>();
        for (Measurement measurement:
             data) {
            String name = measurement.getName();
            if (map.containsKey(name)) {
                map.put(measurement.getName(), map.get(name) + measurement.getValue());
            } else
                map.put(measurement.getName(), measurement.getValue());
        }
        return map;
    }
}
