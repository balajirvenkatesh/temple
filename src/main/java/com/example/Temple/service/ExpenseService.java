package com.example.Temple.service;

import com.example.Temple.model.Expense;
import com.example.Temple.model.ExpenseCategory;
import com.example.Temple.model.Person;
import com.example.Temple.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense){
        Person optionalPersonOwner = expense.getOwner();
        Person optionalPersonVendor = expense.getVendor();
        if(optionalPersonOwner == null){
            throw new EntityNotFoundException("owner id not found..! ");
        }
        if(optionalPersonVendor == null){
            throw new EntityNotFoundException("Vendor id not found..!");
        }
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updateExpense){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            Expense dbExpense = optionalExpense.get();
            dbExpense.setExpenseReference(updateExpense.getExpenseReference());
            dbExpense.setCategory(updateExpense.getCategory());
            dbExpense.setCurrency(updateExpense.getCurrency());
            dbExpense.setOwner(updateExpense.getOwner());
            dbExpense.setVendor(updateExpense.getVendor());
            dbExpense.setStatus(updateExpense.getStatus());
            dbExpense.setTaxAmount(updateExpense.getTaxAmount());
            dbExpense.setTotalAmount(updateExpense.getTotalAmount());
            dbExpense.setReceiptAttachmentUrl(updateExpense.getReceiptAttachmentUrl());
            dbExpense.setUpdatedAt(updateExpense.getUpdatedAt());
            return expenseRepository.save(dbExpense);
        }
        else{
            throw new EntityNotFoundException("Id not found");
        }
    }

    public void deleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            Expense expense = optionalExpense.get();
            expense.setDeletedAt(LocalDateTime.now());
            expenseRepository.save(expense);
        }
        else {
            throw new EntityNotFoundException("Id not found");
        }
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense getExpenseByOwner(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findByOwner(id);
        if(optionalExpense.isPresent()){
            return optionalExpense.get();
        }
        throw new EntityNotFoundException("owner expense are not present");
    }
}
