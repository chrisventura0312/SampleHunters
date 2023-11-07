package com.codingdojo.samplehunters.repositories;

import org.springframework.stereotype.Repository;

import com.codingdojo.samplehunters.models.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.codingdojo.samplehunters.models.Sample;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    
    boolean existsByUserIdAndSampleId(Long userId, Long sampleId);

    List<Like> findBySample(Sample sample);
}
