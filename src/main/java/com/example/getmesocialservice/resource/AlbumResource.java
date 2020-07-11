package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.InvalidIdTokenException;
import com.example.getmesocialservice.model.Album;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.service.AlbumService;
import com.example.getmesocialservice.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumResource {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public Album saveAlbum(@RequestBody @Valid Album album,
                           @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        album.setCreatedBy(firebaseUser.getEmail());

        if (firebaseUser != null){
            return albumService.saveAlbum(album);
        }else {
            throw new InvalidIdTokenException();
            //return null;
        }
    }

    @GetMapping
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    @GetMapping("/find-by-id")
    public Album getById(@RequestParam(name = "albumId") String albumId){
        return albumService.getById(albumId);
    }

    @PutMapping
    public Album updateAlbum(@RequestBody @Valid Album album,
                             @RequestParam(name = "albumId") String albumId,
                             @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Album specificAlbum = albumService.getById(albumId);
        if (firebaseUser != null && firebaseUser.getEmail().equals(specificAlbum.getCreatedBy())){
            return albumService.updateAlbum(album);
        }else {
            throw new InvalidIdTokenException();
            //return null;
        }
    }

    @DeleteMapping
    public void deleteAlbum(@RequestParam(name = "albumId") String albumId,
                            @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Album specificAlbum = albumService.getById(albumId);

        if (firebaseUser != null && firebaseUser.getEmail().equals(specificAlbum.getCreatedBy())){
            albumService.deleteAlbum(albumId);
        }else {
            throw new InvalidIdTokenException();
        }
    }

}
