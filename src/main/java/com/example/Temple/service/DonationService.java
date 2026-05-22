package com.example.Temple.service;

import com.example.Temple.model.Donation;
import com.example.Temple.repository.DonationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public Donation save(Donation donation){
        return donationRepository.save(donation);
    }

    public List<Donation> getAllDonation(){
        return donationRepository.findAll();
    }

    public Donation getDonation(Long id) throws Exception{
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if(optionalDonation.isPresent()){
            return optionalDonation.get();
        }
        throw new EntityNotFoundException("ID: "+id+" not present");
    }
}
