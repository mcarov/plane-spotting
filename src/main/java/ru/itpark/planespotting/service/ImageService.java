package ru.itpark.planespotting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.planespotting.exception.FileUploadException;
import ru.itpark.planespotting.exception.ImageNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;

@Service
public class ImageService {
    private final String uploadPath;

    public ImageService(@Value("${path.upload}") String uploadPath) {
        this.uploadPath = new File(uploadPath).getAbsolutePath();
    }

    public byte[] readImage(String filename) {
        try {
            Path path = Paths.get(uploadPath).resolve(filename);

            return Files.readAllBytes(path);
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new ImageNotFoundException("api.error.no-image");
        }
    }

    public String writeImage(MultipartFile file) {
        try {
            String filename = ZonedDateTime.now().toString()
                    .replaceAll("[^A-Za-z0-9]", "")
                    .replace('T','_')
                    .substring(0, 15);
            file.transferTo(Paths.get(uploadPath).resolve(filename));

            return filename;
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new FileUploadException("api.error.upload");
        }
    }
}
