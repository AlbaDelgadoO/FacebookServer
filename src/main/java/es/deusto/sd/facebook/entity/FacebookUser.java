package es.deusto.sd.facebook.entity;

public class FacebookUser {

    private String email;
    private String password;
    private String name;

    // Constructor, getters, and setters
    public FacebookUser(String email, String password) {
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