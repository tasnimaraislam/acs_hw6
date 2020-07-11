package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.InvalidIdTokenException;
import com.example.getmesocialservice.exception.RestrictedInfoException;
import com.example.getmesocialservice.exception.RestrictedNameException;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.service.FirebaseService;
import com.example.getmesocialservice.service.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public User saveUser(@RequestBody @Valid User user,
                         @RequestHeader (name="idToken") String idToken)
            throws RestrictedNameException, IOException, FirebaseAuthException, InvalidIdTokenException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (user.getName().equalsIgnoreCase("root")) {
            throw new RestrictedNameException();
        }

        if (firebaseUser != null) {
            return userService.saveUser(user);
        } else {
            throw new InvalidIdTokenException();
            //return null;
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/find-by-id")
    public User getById(@RequestParam(name = "userId") String userId){
        return userService.getById(userId);
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user,
                           @RequestHeader (name="idToken") String idToken,
                           @RequestHeader(name="firebaseEmail") String firebaseEmail)
            throws RestrictedNameException, IOException, FirebaseAuthException, InvalidIdTokenException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(user.getName().equalsIgnoreCase("root")){
            throw new RestrictedNameException();
        }
//        System.out.println(firebaseUser.getEmail().equals(firebaseEmail));
//        System.out.println("firebaseEmail: "+ firebaseEmail);
//        System.out.println( "firebaseUser.getEmail(): " + firebaseUser.getEmail());

        if (firebaseUser != null && firebaseUser.getEmail().equals(firebaseEmail)){
            return userService.updateUser(user);
        }else {
            throw new InvalidIdTokenException();
            //return null;
        }
    }

    @DeleteMapping
    public void deleteUser(@RequestParam(name = "userId") String userId,
                           @RequestHeader (name="idToken") String idToken,
                           @RequestHeader(name="firebaseEmail") String firebaseEmail)
            throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null && firebaseUser.getEmail().equals(firebaseEmail)){
            userService.deleteUser(userId);
        }
    }

    @GetMapping("/find-by-address")
    public List<User> getByAddress(@RequestParam(name = "address") String address) throws RestrictedInfoException {
        if (address.equalsIgnoreCase("area51")){
            throw new RestrictedInfoException();
        }
        return userService.getByAddress(address);
    }

//    @GetMapping("/user")
//    public User getUser(){
//        return userService.getUser();
//    }
//    @GetMapping("/user/{userId}")
//    public User getUserById(@PathVariable("userId") int userId){
//        return userService.getUserById(userId);
//    }
//
}
