package com.example.Temple.controller;

import com.example.Temple.model.Donation;
import com.example.Temple.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {
    @Autowired
    private DonationService donationService;

    @PostMapping
    public ResponseEntity<?> saveDonation(@RequestBody Donation donation){
        Donation savedDonation = donationService.save(donation);
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllDonation(){
        List<Donation> donationList = donationService.getAllDonation();
        return new ResponseEntity<>(donationList, HttpStatus.FOUND);
    }

    @GetMapping("/id")
    public ResponseEntity<?>getDonationById(@RequestParam Long id){
        try {
            Donation donation = donationService.getDonation(id);
            return new ResponseEntity<>(donation, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
