package com.isaproject.isaproject.Controller;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/api/erecipes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EPrescriptionController {

    @PostMapping("/file")
    public String hello(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                System.out.println(file.getOriginalFilename());
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File("src/main/resources/qr/" + file.getOriginalFilename()); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
                ImageIO.write(src, "png", destination);
                String decodedText = decodeQRCode(new File("src/main/resources/qr/" + file.getOriginalFilename()));
                if (decodedText == null) {
                    return "No QR Code found in the image";
                } else {
                    return "\"Decoded text = \" + decodedText";
                }
            } catch (IOException | NotFoundException e) {
                return "";
            }
        }return "";
    }

    private static String decodeQRCode(File qrCodeimage) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException n) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }
}


