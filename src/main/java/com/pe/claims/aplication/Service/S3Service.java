package com.pe.claims.aplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class S3Service {
    @Autowired
    private S3Client s3Client;


    @Value("${s3.name}")
    private String bucketName;

    public String UploadFile ( MultipartFile file ) throws IOException {

        String keyName = file.getOriginalFilename();
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(Objects.requireNonNull(file.getOriginalFilename()));
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        //PutObjectResponse response = s3Client.putObject(putObjectRequest,path);
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        //s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(),putObjectRequest));
        return  "https://" + bucketName + ".s3.amazonaws.com/" + keyName;
    }
}
