package com.jeweleryguard.service;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.MyFile;
import com.jeweleryguard.model.User;
import com.jeweleryguard.repository.MyFileRepository;
import com.jeweleryguard.repository.UserRepository;
import groovy.util.MapEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sun.plugin.javascript.navig.JSType.Image;

@Service("MyFileService")
public class MyFileServiceImpl implements MyFileService{
	@Autowired
	private MyFileRepository myFileRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${resources.public.img-dir}")
	private String imgDir;

	@Value("${resources.public.j-img-prefix}")
	private String jImgPrefix;

	@Value("${resources.public.j-img-sufix}")
	private String jImgSufix;

	@Override
	public byte[] readJewelryImage(File file) {
		return new byte[0];
	}

	@Override
	public MyFile saveJewelryImage(String jImgBase, Jewelry jewelry) throws FileNotFoundException {
		if ( jImgBase == null  || jImgBase == "") {
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
			writeToSystem(jImgBase, imgPath );
		} catch (FileNotFoundException e) {
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

	public boolean writeToSystem(String base64String, String file) throws FileNotFoundException {
		byte[] bytes = Base64.getDecoder().decode(base64String);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.close();
		return true;
	}
	@Override
	public List<MyFile> findAll() {
		return (List<MyFile>) myFileRepository.findAll();
		}

	@Override
	public MyFile findByName(String name) {
		return myFileRepository.findByName(name);
	}

	@Override
	public MyFile save(MyFile file) {
		return myFileRepository.save(file);
	}

	

}
