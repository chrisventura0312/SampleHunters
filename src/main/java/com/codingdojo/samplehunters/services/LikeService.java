package com.codingdojo.samplehunters.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.samplehunters.repositories.LikeRepository;
import com.codingdojo.samplehunters.models.Like;
import com.codingdojo.samplehunters.models.Sample;
import com.codingdojo.samplehunters.models.User;
import com.codingdojo.samplehunters.repositories.SampleRepository;
import com.codingdojo.samplehunters.repositories.UserRepository;

@Service 
public class LikeService {

    @Autowired LikeRepository likeRepo;
    @Autowired SampleRepository sampleRepository;
    @Autowired UserRepository userRepository;

    public boolean hasUserLikedSample(Long userId, Long sampleId) {
        return likeRepo.existsByUserIdAndSampleId(userId, sampleId);
    }

    public boolean hasUserLikedSample(Sample sample, User user) {
        return likeRepo.existsByUserIdAndSampleId(user.getId(), sample.getId());
    }
    

    public Like createLike(Like newLike) {
        return likeRepo.save(newLike);
    }

    public void likeSample(Long sampleId, Long userId) {
        Sample sample = sampleRepository.findById(sampleId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
    
        if (sample != null && user != null) {
            // Check if the user has already liked the sample
            if (!hasUserLikedSample(sample, user)) {
                Like like = new Like(sample, user);
                likeRepo.save(like);
    
                // Increment the likesCount property
                sample.setLikesCount(sample.getLikesCount() + 1);
                sampleRepository.save(sample);
            }
        }
    }
    
    
}
