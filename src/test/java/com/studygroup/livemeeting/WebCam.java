package com.studygroup.livemeeting;
import lombok.extern.slf4j.Slf4j;
import nu.pattern.OpenCV;
import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@SpringBootTest
@Slf4j
public class WebCam {

    @Test
    public void getJPGFrameFromWebCam() throws IOException {
        OpenCV.loadLocally();
        VideoCapture vc = new VideoCapture(0);
        Mat frame = new Mat(1000, 1000, 1000);
        while(true) {
            vc.read(frame);
            BufferedImage image = matToBufferedImage(frame);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            byte[] bytes = outputStream.toByteArray();
            ByteArrayInputStream inStream = new ByteArrayInputStream(bytes);
            log.info(String.valueOf(inStream.readNBytes(1)));
<<<<<<< HEAD
            BufferedImage newImage = ImageIO.read(inStream);
            ImageIO.write(newImage, "jpg", new File("/Users/ung/Downloads/test.jpg"));
=======
//            BufferedImage newImage = ImageIO.read(inStream);
//            ImageIO.write(newImage, "jpg", new File("/Users/ung/Downloads/test.jpg"));
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
        }
    }



    @Test
    public void accessCam() {
        OpenCV.loadLocally();
        VideoCapture vc = new VideoCapture(0);
        if (vc.isOpened()) {
            assert(true);
        }
        assert(false);
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
