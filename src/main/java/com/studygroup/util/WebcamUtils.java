package com.studygroup.util;

import com.github.sarxos.webcam.Webcam;
import com.studygroup.config.VideoSocketHandler;
import lombok.extern.java.Log;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;


@Log
public class WebcamUtils {

    private static volatile boolean isSending = false;


    public static void startSendingVideo(String url) {
        WebSocketClient client = new StandardWebSocketClient();
        isSending = true;

        try {
            URI uri = new URI(url);
            WebSocketSession session = client.doHandshake(new VideoSocketHandler(), null, uri).get();

            OpenCV.loadLocally();
            VideoCapture vc = new VideoCapture(0);
            log.info(String.valueOf(vc.isOpened()));
            Mat frame = new Mat();

//            while (isSending) {
            while(session.isOpen()) {
//                vc.read(frame);  // Move this line here to continuously read new frames
//                BufferedImage image = matToBufferedImage(frame);
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                ImageIO.write(image, "jpg", outputStream);
//                byte[] bytes = outputStream.toByteArray();
//                String messageStr = Base64.getEncoder().encodeToString(bytes);
                TextMessage message = new TextMessage("hello");
                session.sendMessage(message);
//            }
//            vc.release();
//            session.close();
            }

        } catch (URISyntaxException | InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopSendingVideo() {
        isSending = false;
    }

    private static BufferedImage matToBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            Mat m = new Mat();
            Imgproc.cvtColor(matrix, m, Imgproc.COLOR_BGR2RGB);
            type = BufferedImage.TYPE_3BYTE_BGR;
            matrix = m;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer); // get all the pixels
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);

        return image;
    }
}

