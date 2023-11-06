package com.codingdojo.samplehunters.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingdojo.samplehunters.models.Sample;
import com.codingdojo.samplehunters.repositories.SampleRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;


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

    //stretch
    public List<Sample> getAllSamplesSortedById() {
        return sampleRepo.findAllByOrderByIdAsc();
    }

    public Sample getNextSample(Sample currentSample) {
        List<Sample> allSamples = getAllSamplesSortedById();
        int currentIndex = -1;
    
        for (int i = 0; i < allSamples.size(); i++) {
            if (allSamples.get(i).getId().equals(currentSample.getId())) {
                currentIndex = i;
                break;
            }
        }
    
        if (currentIndex >= 0 && currentIndex < allSamples.size() - 1) {
            return allSamples.get(currentIndex + 1);
        } else {
            return null;
        }
    }

    public Sample getPreviousSample(Sample currentSample) {
        List<Sample> allSamples = getAllSamplesSortedById();
        int currentIndex = -1;
    
        for (int i = 0; i < allSamples.size(); i++) {
            if (allSamples.get(i).getId().equals(currentSample.getId())) {
                currentIndex = i;
                break;
            }
        }
    
        if (currentIndex > 0) {
            return allSamples.get(currentIndex - 1);
        } else {
            return null; // No previous sample found
        }
    }
    
}