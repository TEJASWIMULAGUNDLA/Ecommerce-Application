package com.eagle.productservice.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dutblxxrx",
                "api_key", "869846253261154",
                "api_secret", "9MsFmC-Dz1MmqI7nVtYpxMw98rU"
        ));
    }

    public String uploadFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(tempFile);

        Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();  // public image URL
    }
}

