package com.example.Temple.controller;

import com.example.Temple.model.ExpenseCategory;
import com.example.Temple.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense_category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @GetMapping
    public ResponseEntity<?> getAll (){
        return new ResponseEntity<>(expenseCategoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ExpenseCategory expenseCategory){
        return new ResponseEntity<>(expenseCategoryService.save(expenseCategory), HttpStatus.CREATED);
    }
}
