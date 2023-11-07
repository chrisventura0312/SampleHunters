package com.codingdojo.samplehunters.models;

//Jakarta Persistence API
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Column;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

//Java
import java.util.List;
import java.util.Date;

//Jakarta Bean Validation API
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//Spring
import org.springframework.format.annotation.DateTimeFormat;

//Local
import com.codingdojo.samplehunters.models.Sample;

    
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Email is required!")
    @Email(message = "Please enter a valid email!")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Username is required!")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Column(name = "userName")
    private String userName;

    @NotEmpty(message = "Password is required!")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    @Column(name = "password")
    private String password;

    @Transient
    @NotEmpty(message = "Confirm Password is required!")
    @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
    private String confirm;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sample> samples;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

    
    //Constructors
    public User() {}
    
    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String Confirm) {
        this.confirm = Confirm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> Samples) {
        this.samples = Samples;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate 
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}