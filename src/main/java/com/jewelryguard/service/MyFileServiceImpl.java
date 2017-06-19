package com.jewelryguard.service;

import com.google.common.collect.Lists;
import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.MyFile;
import com.jewelryguard.repository.MyFileRepository;
import com.jewelryguard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Service("MyFileService")
public class MyFileServiceImpl implements MyFileService{
	@Autowired
	private MyFileRepository myFileRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${resources.public.img-dir}")
	private String imgDir;

	@Value("${resources.img.jewelery-prefix}")
	private String jImgPrefix;

	@Value("${resources.img.sufix}")
	private String jImgSufix;

	@Override
	public MyFile saveJewelryImage(MultipartFile file, Jewelry jewelry) throws FileNotFoundException {
		if ( file == null ) {
			return null;
        }
        MyFile myFile = new MyFile(jewelry);
        myFile = myFileRepository.save(myFile);
        String filePath = imgDir + jImgPrefix + jewelry.getId() + "/";
        String fileName = file.getName()+"_"+myFile.getId()+".png";

		if ( myFile == null ){
			throw new EntityNotFoundException("#Database my_file create exception");
		}

		try {
			writeToSystem(file.getBytes(), filePath, fileName );
		} catch (IOException e) {
			myFileRepository.delete(myFile);
			throw new FileNotFoundException("#File not found or #IOException ");
		}

		myFile.setPath(jewelry.getId()+"/"+myFile.getId());
		myFile = myFileRepository.save(myFile);

		if ( myFile == null ){
			throw new EntityNotFoundException("#Database my_file update exception");
		}

		return myFile;
	}
    @Override
    public List<MyFile> findAllByJewelry(Jewelry jewelry) {
        return myFileRepository.findAllByJewelry(jewelry);
	}

	@Override
	public MyFile findOne(int imgId) {
		return myFileRepository.findOne(imgId);
	}

	@Override
	public void delete(MyFile myFile) {
		myFileRepository.delete(myFile);
	}

	public boolean writeToSystem(byte[] bytes, String filePath, String fileName) throws IOException {
		File folder = new File(filePath);
		if ( !folder.exists() ) {
            folder.mkdirs();
        }
		FileOutputStream fos = new FileOutputStream(filePath+fileName);

        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage image = ImageIO.read(in);
        try (OutputStream os = fos) {
            ImageIO.write(image, "png", os);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        fos.close();
        return true;
	}
	public static byte[] readFromSystem(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        byte[] bytes = Files.readAllBytes(path);
        if ( bytes == null ) {
            throw new IOException();
        }
        return bytes;

    }
	@Override
	public List<MyFile> findAll() {
		return Lists.newArrayList( (List<MyFile>) myFileRepository.findAll());
		}



	

}
