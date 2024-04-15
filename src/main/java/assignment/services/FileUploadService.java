package assignment.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileUploadService {
    String upload(MultipartFile file) throws IOException;

}
