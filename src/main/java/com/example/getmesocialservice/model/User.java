package com.example.getmesocialservice.model;

import com.example.getmesocialservice.validation.ValidName;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class User {
    @Id
    private String id;
    @NotEmpty @ValidName
    private String name;
    @Email @NotEmpty
    private String email;
    @NotEmpty
    private String profilePicUrl;
    @Min(value = 13) @Max(150)
    private int age;
    @Length(max = 50) @NotEmpty
    private String address;

    public User(String id, @NotEmpty String name, @Email @NotEmpty String email, @NotEmpty String profilePicUrl, @Min(value = 13) @Max(150) int age, @Length(max = 50) @NotEmpty String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePicUrl = profilePicUrl;
        this.age = age;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
