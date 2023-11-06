package com.codingdojo.samplehunters.repositories;

import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.samplehunters.models.Sample;

@Repository
public interface SampleRepository extends CrudRepository<Sample, Long> {

    List<Sample> findAll();

    List<Sample> findByUserId(Long id);

    List<Sample> findAllByOrderByIdAsc();
    


}
