package com.jewelryguard.controller;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.MyFile;
import com.jewelryguard.service.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/img")
public class JImageController {

    @Autowired
    private JewelryService jewelryService;
    @Autowired
    private MyFileService myFileService;

    @RequestMapping(value="/{jewelryId}/{imgId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteOneImage(@PathVariable("jewelryId") int jewelryId, @PathVariable("imgId") int imgId) {
        ModelAndView modelAndView = new ModelAndView();
        Jewelry jewelry = jewelryService.findOne(jewelryId);
        if (jewelry == null) {
            throw new EntityNotFoundException("Not found jewewlry for id: " + jewelryId);
        }
        MyFile image = myFileService.findOne(imgId);
        if ( image.getJewelry().getId() != jewelry.getId()){
            throw new EntityNotFoundException("BLABLABLA");
        }

        myFileService.delete(image);
    }
    @RequestMapping(value="/{jewelryId}/{imgId}", method = RequestMethod.GET, produces = "image/png")
    public @ResponseBody
    ResponseEntity<byte[]> getFile(@PathVariable("jewelryId") int jewelryId, @PathVariable("imgId") int imgId) {
            try {
                // Retrieve images from the classpath.
                Path path = Paths.get("/jguard/public/img/j_img_"+jewelryId+"/image_"+imgId+".png");


                byte[] bytes = Files.readAllBytes(path);

                // Set headers
                final HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);

                return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }


}
