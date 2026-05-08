package com.example.Temple.service;

import com.example.Temple.model.ExpenseCategory;
import com.example.Temple.repository.ExpenseCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategory save(ExpenseCategory expenseCategory){
        return expenseCategoryRepository.save(expenseCategory);
    }

    public List<ExpenseCategory> getAll(){
        return expenseCategoryRepository.findAll();
    }

    public ExpenseCategory findById(Long id){
        Optional<ExpenseCategory> optionalExpenseCategory = expenseCategoryRepository.findById(id);
        if(optionalExpenseCategory.isPresent()){
            return optionalExpenseCategory.get();
        }
        throw new EntityNotFoundException("Id not found");
    }
}
