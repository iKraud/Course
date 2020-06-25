/**
 * @author Sharafan Oksana
 * @date 09.02.2020
 * @package ru.sushi.demo.uploadingfiles.storage
 */
package ru.sushi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.sushi.demo.exceptions.StorageException;
import ru.sushi.demo.exceptions.StorageFileNotFoundException;
//import ru.sushi.demo.uploadingfiles.storage.StorageProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service ("fileUploadinService")
public class FileUploadingServiceImpl implements FileUploadingService {
    private final Path rootLocation;

//    Environment environment;
//    @Value("sushi-ru.imagesHandlerPath")
//    private String imageUploadPath;
//
//
//    @Autowired
//    public FileUploadingServiceImpl() {
////        this.environment = environment;
//        this.rootLocation = Paths.get(environment.getProperty("sushi-ru.imagesHandlerPath"));
////        this.rootLocation = Paths.get(environment.getProperty("sushi-ru.imagesResourcesPath"));
//    }

    @Autowired
    public FileUploadingServiceImpl() {
//        this.rootLocation = Paths.get("C:\\temp\\images\\");
        this.rootLocation = Paths.get("src/main/resources/static/images");
    } //todo прописать путь в aplication.property

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }
}
