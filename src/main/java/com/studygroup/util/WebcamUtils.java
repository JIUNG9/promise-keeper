package com.studygroup.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;


public class WebcamUtils {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static boolean sendWebcamVideo(String url, Long userId, UUID roomId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        VideoCapture camera = new VideoCapture(0);
        Mat frame = new Mat();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if (!camera.isOpened()) {
            System.out.println("Failed to open camera!");
            return false;
        }

        while (true) {
            camera.read(frame);
            if (!frame.empty()) {
                try {
                    BufferedImage image = matToBufferedImage(frame);
                    ImageIO.write(image, "jpg", outputStream);
                    byte[] bytes = outputStream.toByteArray();
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                    ByteArrayResource videoFile = new ByteArrayResource(bytes) {
                        @Override
                        public String getFilename() {
                            return "frame.jpg";
                        }
                    };
                    body.add("file", videoFile);
                    body.add("userId", userId);
                    body.add("roomId", roomId);

                    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
                    ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
                    body.clear();
                    outputStream.reset();
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Failed to send video frame: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to capture video frame!");
            }
        }
    }

    private static BufferedImage matToBufferedImage(Mat mat) {
        byte[] data = new byte[mat.cols() * mat.rows() * (int) mat.elemSize()];
        mat.get(0, 0, data);
        int type = mat.channels() == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR;
        if (type == BufferedImage.TYPE_3BYTE_BGR) {
            for (int i = 0; i < data.length; i += 3) {
                byte blue = data[i];
                data[i] = data[i + 2];
                data[i + 2] = blue;
            }
        }
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
        return image;
    }
}