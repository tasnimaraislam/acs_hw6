package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.InvalidIdTokenException;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.model.Photo;
import com.example.getmesocialservice.service.FirebaseService;
import com.example.getmesocialservice.service.PhotoService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoResource {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public Photo savePhoto(@RequestBody @Valid Photo photo,
                           @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        photo.setCreatedBy(firebaseUser.getEmail());

        if (firebaseUser != null){
            return photoService.savePhoto(photo);
        }else {
            throw new InvalidIdTokenException();
        }
    }

    @GetMapping
    public List<Photo> getAllPhotos(){
        return photoService.getAllPhotos();
    }

    @GetMapping("/find-by-id")
    public Photo getById(@RequestParam(name = "photoId") String photoId){
        return photoService.getById(photoId);
    }

    @PutMapping
    public Photo updatePhoto(@RequestBody @Valid Photo photo,
                             @RequestParam(name = "photoId") String photoId,
                             @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Photo specificPhoto = photoService.getById(photoId);

        if (firebaseUser != null && firebaseUser.getEmail().equals(specificPhoto.getCreatedBy())){
            return photoService.updatePhoto(photo);
        }else {
            throw new InvalidIdTokenException();
        }
    }

    @DeleteMapping
    public void deletePhoto(@RequestParam(name= "photoId") String photoId,
                            @RequestHeader (name="idToken") String idToken)
            throws IOException, FirebaseAuthException, InvalidIdTokenException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Photo specificPhoto = photoService.getById(photoId);

        if (firebaseUser != null && firebaseUser.getEmail().equals(specificPhoto.getCreatedBy())){
            photoService.deletePhoto(photoId);
        }else {
            throw new InvalidIdTokenException();
        }
    }
}
