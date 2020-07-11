package com.example.getmesocialservice;

import com.example.getmesocialservice.model.Album;
import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.service.AlbumService;
import com.example.getmesocialservice.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTests {
	@Autowired
	private AlbumService albumService;

	@Before
	public void saveAlbum(){
		albumService.saveAlbum(new Album("1", "album1",
				"cpurl1", "tasnim", new Date()));
	}

	@Test
	public void getAlbum(){
		Album album = albumService.getById("1");
		Assert.assertEquals("album1", album.getName());
	}

	@Test
	public void editAlbum(){
		Album album  = albumService.getById("1");
		album.setName("album2");
		albumService.saveAlbum(album);
		Assert.assertEquals("album1", album.getName());
	}

	@After
	public void delete(){
		albumService.deleteAlbum("1");
	}
}
