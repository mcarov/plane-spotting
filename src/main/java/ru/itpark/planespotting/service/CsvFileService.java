package ru.itpark.planespotting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itpark.planespotting.entity.*;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvFileService {
    private final Path path;

    public CsvFileService(@Value("${path.resources}") String uploadPath){
        path = Paths.get(new File(uploadPath).getAbsolutePath())
                .resolve("planes-mod.csv");
    }

    public List<PhotoEntity> importFromCsvFile() throws IOException, ParseException {
        try(Reader reader = Files.newBufferedReader(path);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withIgnoreSurroundingSpaces()
                    .withTrim())) {
            List<PhotoEntity> list = new ArrayList<>();
            for(CSVRecord record : parser) {
                list.add(getPhotoEntity(record));
            }
            return list;
        }
    }

    private PhotoEntity getPhotoEntity(CSVRecord record) throws ParseException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        ObjectMapper mapper = new ObjectMapper();

        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(NumberUtils.toLong(record.get("id")));
        photoEntity.setFilename(record.get("filename"));
        photoEntity.setPhotographer(record.get("photographer"));
        photoEntity.setDate(format.parse(record.get("date")));
        photoEntity.setPlane(mapper.readValue(record.get("plane"), PlaneEntity.class));
        photoEntity.setAirline(mapper.readValue(record.get("airline"), AirlineEntity.class));
        photoEntity.setAirport(mapper.readValue(record.get("airport"), AirportEntity.class));

        return photoEntity;
    }
}
