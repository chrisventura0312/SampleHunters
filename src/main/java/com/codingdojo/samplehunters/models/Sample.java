package com.codingdojo.samplehunters.models;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;


//Validation
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.codingdojo.samplehunters.models.User;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Sample Name is required!")
    @Size(min = 3, max=100, message = "Sample Name must be at least 3 characters")
    @Column(name = "sample_name")
    private String sampleName;

    @NotEmpty(message = "Sample artist is required!")
    @Size(min = 3, max=100, message = "Sample artist must be at least 3 characters")
    @Column(name = "sample_artist")
    private String sampleArtist;

    @NotEmpty(message = "Sample link is required!")
    @Size(min = 3, max=200, message = "Sample link must be at least 3 characters")
    @Column(name = "sample_link")
    private String sampleLink;

    @NotEmpty(message = "You left it empty!")
    @Size(min = 3, max=200, message = "Must provide where you heard the sample!")
    @Column(name = "sample_source")
    private String sampleSource;

    @NotEmpty(message = "You left it empty!")
    @Size(min = 3, max=200, message = "Must provide a description of where you heard the sample.")
    @Column(name = "sample_source_artist")
    private String sampleSourceArtist;

    @NotEmpty(message = "You left it empty!")
    @Size(min = 3, max=200, message = "We need a link for the source!")
    @Column(name = "sample_source_link")
    private String sampleSourceLink;

    @NotEmpty(message = "You left it empty!")
    @Size(min = 3, max=1000, message = "Must provide a description of where you heard the sample.")
    @Column(name = "sample_description")
    private String sampleDescription;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;
    
    @OneToMany(mappedBy = "sample", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

    @Column(name = "likes")
    private int likesCount = 0;






    //constructor
    public Sample() {
        this.likesCount = 0;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public String getSampleName() {
        return sampleName;
    }

    public String getSampleArtist() {
        return sampleArtist;
    }


    public String getSampleLink() {
        return sampleLink;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public String getSampleSourceArtist() {
        return sampleSourceArtist;
    }

    public String getSampleSourceLink() {
        return sampleSourceLink;
    }

    public User getUser() {
        return user;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public void setSampleArtist(String sampleArtist) {
        this.sampleArtist = sampleArtist;
    }


    public void setSampleLink(String sampleLink) {
        this.sampleLink = sampleLink;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public void setSampleSourceArtist(String sampleSourceArtist) {
        this.sampleSourceArtist = sampleSourceArtist;
    }

    public void setSampleSourceLink(String sampleSourceLink) {
        this.sampleSourceLink = sampleSourceLink;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }



    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
