package org.weewelchie.qr.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weewelchie.qr.codegen.QRCodeGenerator;
import org.weewelchie.qr.persistence.QRCodeData;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/barcodes")
public class BarCodeController {

    @Autowired
    QRCodeGenerator qrCodeGenerator ;

    @PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrgenQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(QRCodeGenerator.generateQRCodeImage(barcode));
    }


    @GetMapping(value = "/generatecodes")
    public List<QRCodeData> generateCodes(@RequestParam String data, @RequestParam Integer numCodes)
    {
        return qrCodeGenerator.generateCodeData(data,numCodes);
    }

    @GetMapping(value = "/allcodes")
    public List<QRCodeData> getAllCodes()
    {
        return qrCodeGenerator.getAllCodes();
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello Barcode Gen";
    }
}
