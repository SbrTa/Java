package com.example.image.service;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class BaseService {
    public String convertMultipartToBase64Img(MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        return "data:image/*;base64,"+Base64.getEncoder().encodeToString(fileContent);
    }

    public String convertInputStreamToBase64Img(InputStream input) throws IOException {
        byte[] fileContent = IOUtils.toByteArray(input);
        return "data:image/*;base64,"+Base64.getEncoder().encodeToString(fileContent);
    }
}
