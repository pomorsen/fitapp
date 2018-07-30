package pl.sda.fitapp.domains.dto;


public class GymUserLoginDto {
    private String email;
    private String password;

    public GymUserLoginDto() {
    }

    public GymUserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
