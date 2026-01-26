package com.ace.personal_chat_platform.dto;

import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @Size(max = 200, message = "Bio can be max 200 characters")
    private String bio;

    @Size(max = 500, message = "Profile photo URL too long")
    private String profilePhoto;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
