package com.spc.jpa.vo;

import org.joda.time.LocalDateTime;
import org.springframework.util.Base64Utils;

import com.spc.jpa.domain.user.User;

public class UserToken {

    private String key;
    private String email;
    private String name;
    private LocalDateTime dateTime = new LocalDateTime();

    // constructor..
    public UserToken() {
    }

    public UserToken(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.key = generateKey();
    }

    public UserToken(String email, String name) {
        this.email = email;
        this.name = name;
        this.key = generateKey();
    }

    public String generateKey() {
        return Base64Utils.encodeToString((this.email + this.name + dateTime.now()).getBytes());
    }

    // getter and setter..
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// toString..
	@Override
	public String toString() {
		return "UserToken [key=" + key + ", email=" + email + ", name=" + name + "]";
	}
    
}
