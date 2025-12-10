package com.geo_surveys.geo_surveys_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.util.Base64;
import java.util.UUID;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    /**
     * Uploads base64 encoded video to S3 storage
     *
     * @param base64Video Base64 encoded video string.
     * @param key is unique key, autogeneration if empty.
     * @param format file extension.
     * @return S3 object key (file path in bucket).
     * @throws IllegalArgumentException if upload fails.
     */
    public String uploadVideo(String base64Video, String key, String format) throws IllegalArgumentException {
        try {
            // Validate input
            validateBase64Video(base64Video);

            // Generate unique file name
            if (key.isEmpty()) {
                key = generateUUIDKey(format);
            }

            // Detect MIME type
            String mimeType = detectMimeType(format);

            // Upload to S3
            uploadToS3(decodeBase64Video(base64Video), key, mimeType);
            return key;

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неправильные данные: " + e.getMessage(), e);
        }
    }

    /**
     * Validates base64 video string
     *
     * @throws IllegalArgumentException if validate fails
     */
    private void validateBase64Video(String base64Video) throws IllegalArgumentException {
        if (base64Video == null || base64Video.trim().isEmpty()) {
            throw new IllegalArgumentException("Видеофайл пуст");
        }

        // Basic base64 validation
        if (!base64Video.matches("^[A-Za-z0-9+/]*={0,2}$")) {
            throw new IllegalArgumentException("Неправильный формат base64");
        }
    }

    /**
     * Decodes base64 string to byte array
     *
     * @throws IllegalArgumentException if decode fails
     */
    private byte[] decodeBase64Video(String base64Video) throws IllegalArgumentException {
        try {
            return Base64.getDecoder().decode(base64Video);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неправильная кодировка base64", e);
        }
    }

    /**Generates unique S3 key for the video file
     */
    private String generateUUIDKey(String fileExtension) {
        String uuid = UUID.randomUUID().toString();
        return String.format("%s.%s", uuid, fileExtension);
    }

    /**
     * Detects MIME type based on file extension
     */
    private String detectMimeType(String fileExtension) {
        return switch (fileExtension.toLowerCase()) {
            case "mp4" -> "video/mp4";
            case "avi" -> "video/x-msvideo";
            case "mov" -> "video/quicktime";
            case "mkv" -> "video/x-matroska";
            case "webm" -> "video/webm";
            default -> "application/octet-stream";
        };
    }

    /**
     * Uploads video bytes to S3
     */
    private void uploadToS3(byte[] videoBytes, String s3Key, String mimeType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .contentType(mimeType)
                .contentLength((long) videoBytes.length)
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromBytes(videoBytes));
    }

    /**
     * Delete video from S3
     */
    public void deleteFromS3(String s3Key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }
}