package com.example.getmesocialservice;

import com.example.getmesocialservice.model.Photo;
import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.service.PhotoService;
import com.example.getmesocialservice.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoServiceTests {
	@Autowired
	private PhotoService photoService;

	@Before
	public void savePhoto(){
		photoService.savePhoto(new Photo("1", "album1",
				"Url1", "tasnim", new Date()));
	}

	@Test
	public void getPhoto(){
		Photo photo = photoService.getById("1");
		Assert.assertEquals("tasnim", photo.getCreatedBy());
	}

	@Test
	public void editPhoto(){
		Photo photo = photoService.getById("1");
		photo.setCreatedBy("mou");
		photoService.savePhoto(photo);
		Assert.assertEquals("tasnim", photo.getCreatedBy());
	}

	@After
	public void delete(){
		photoService.deletePhoto("2");
	}
}
