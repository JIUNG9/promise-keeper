package com.studygroup.util.constant;

import lombok.extern.slf4j.Slf4j;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Slf4j
public class WebCamDataHandler {

    public String encodeWebCamData() throws IOException {

        OpenCV.loadLocally();
        VideoCapture vc = new VideoCapture(0);
        log.info(String.valueOf(vc.isOpened()));
        Mat frame = new Mat();

            vc.read(frame);

            BufferedImage image = matToBufferedImage(frame);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);

            byte[] bytes = outputStream.toByteArray();
            String messageStr = Base64.getEncoder().encodeToString(bytes);

            return messageStr;

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
