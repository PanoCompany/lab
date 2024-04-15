package assignment.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.core.io.UrlResource;
import java.io.File;
import java.net.MalformedURLException;

@Controller
public class FileController {
    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws MalformedURLException {
        File file = new File("C://uploads/" + fileName);
        Resource resource = new UrlResource(file.toURI());
        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok().body(resource);
        } else {
            throw new RuntimeException("Failed to load image: " + fileName);
        }
    }
}
