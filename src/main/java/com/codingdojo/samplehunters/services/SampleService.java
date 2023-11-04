package com.codingdojo.samplehunters.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingdojo.samplehunters.models.Sample;
import com.codingdojo.samplehunters.repositories.SampleRepository;

@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepo;

    public List<Sample> getAllSamples() {
        return sampleRepo.findAll();
    }

    public Sample getSingleSample(Long id) {
        return sampleRepo.findById(id).orElse(null);
    }

    public Sample createSample(Sample newSample) {
        return sampleRepo.save(newSample);
    }

    public Sample updateSample(Sample updatedSample) {
        return sampleRepo.save(updatedSample);
    }

    public void deleteSample(Long id) {
        sampleRepo.deleteById(id);
    }

    public List<Sample> getSamplesByUserId(Long id) {
        return sampleRepo.findByUserId(id);
    }

}