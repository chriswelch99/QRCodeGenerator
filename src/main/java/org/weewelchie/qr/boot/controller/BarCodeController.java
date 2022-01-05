package org.weewelchie.qr.boot.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weewelchie.qr.codegen.QRCodeGenerator;
import org.weewelchie.qr.persistence.QRCodeData;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@Tag(name = "QRCode Generator")
@RequestMapping("/barcodes")
public class BarCodeController {

    @Autowired
    QRCodeGenerator qrCodeGenerator ;

    @PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Generates a QR Code with the Body text as it's content") })
    public ResponseEntity<BufferedImage> qrgenQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(QRCodeGenerator.generateQRCodeImage(barcode));
    }


    @GetMapping(value = "/generatecodes")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Generate a set of data for QR codes.") })
    public List<QRCodeData> generateCodes(@RequestParam String data, @RequestParam Integer numCodes)
    {
        return qrCodeGenerator.generateCodeData(data,numCodes);
    }

    @GetMapping(value = "/allcodes")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get all QRCodes stored in the DB") })
    public List<QRCodeData> getAllCodes()
    {
        return qrCodeGenerator.getAllCodes();
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/hello")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Say Hello") })
    public String hello()
    {
        return "Hello Barcode Gen";
    }
}
