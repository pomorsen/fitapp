package pl.sda.fitapp.domains.dto;

import pl.sda.fitapp.domains.UserType;

public class LoggedUserDto {
    private String username;
    private UserType userType;

    public LoggedUserDto(String username, UserType userType) {
        this.username = username;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
