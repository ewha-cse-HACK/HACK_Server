package com.hack.hack_server.Global.S3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class S3Uploader {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String getPreSignedURL(String dirname){
        String preSignedURL = "";
        String fileName = dirname + "/" + UUID.randomUUID().toString();

        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 2; //만료시간 2분
        expiration.setTime(expTimeMillis);

        try {
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucket, fileName)
                            .withMethod(HttpMethod.PUT)
                            .withExpiration(expiration);

            generatePresignedUrlRequest.addRequestParameter(
                    Headers.S3_CANNED_ACL,
                    CannedAccessControlList.PublicRead.toString());

            URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
            preSignedURL = url.toString();
        } catch (Exception e){
            e.printStackTrace();
        }

        return preSignedURL;
    }

//    private String upload(File uploadFile, String dirName){
//        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();
//        String uploadImageUrl = putS3(uploadFile, fileName);
//        removeImage(uploadFile);
//        return uploadImageUrl;
//    }

//    private String putS3(File uploadFile, String fileName){
//        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
//        log.info("File Upload : " + fileName);
//        return amazonS3Client.getUrl(bucket, fileName).toString();
//    }


//    private void removeImage(File targetFile){
//        if (targetFile.delete()){
//            log.info("File delete success");
//            return;
//        }
//        log.info("File delete fail");
//    }

}
