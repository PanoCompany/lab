package assignment.services.impl;

import assignment.services.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String upload(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            File uploadDir = new File("C://uploads/");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = generateFileName(Objects.requireNonNull(file.getOriginalFilename()));

            File newFile = new File(uploadDir, fileName);
            try (OutputStream outputStream = Files.newOutputStream(newFile.toPath())) {
                outputStream.write(file.getBytes());
            }
            return fileName;
        }
        return null;
    }


    private String generateFileName(String file) {
        String ext = file.replaceAll("[^a-zA-Z0-9]", "");
        return System.currentTimeMillis() + ext;
    }
}
