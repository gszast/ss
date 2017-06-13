package com.jewelryguard.service;

import com.google.common.collect.Lists;
import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.MyFile;
import com.jewelryguard.repository.MyFileRepository;
import com.jewelryguard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
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
	public MyFile saveJewelryImage(String imageBase64, Jewelry jewelry) throws FileNotFoundException {
		if ( imageBase64 == null  || imageBase64 == "") {
			return null;
		}
		String imgPath = imgDir + jImgPrefix + jewelry.getId() + "/";

		MyFile myFile = new MyFile(jewelry);
		myFile = myFileRepository.save(myFile);

		if ( myFile == null ){
			throw new EntityNotFoundException("#Database my_file create exception");
		}

		imgPath += myFile.getId() + jImgSufix;

		try {
			writeToSystem(imageBase64, imgPath );
		} catch (IOException e) {
			myFileRepository.delete(myFile);
			throw new FileNotFoundException("#File not found or #IOException ");
		}

		myFile.setPath(imgPath);
		myFile = myFileRepository.save(myFile);

		if ( myFile == null ){
			throw new EntityNotFoundException("#Database my_file update exception");
		}

		return myFile;
	}

	public boolean writeToSystem(String base64String, String file) throws IOException {
		byte[] bytes = Base64.getDecoder().decode(base64String);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.close();
		return true;
	}
	@Override
	public List<MyFile> findAll() {
		return Lists.newArrayList( (List<MyFile>) myFileRepository.findAll());
		}



	

}
