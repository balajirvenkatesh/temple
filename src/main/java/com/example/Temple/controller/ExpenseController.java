package com.example.Temple.controller;

import com.example.Temple.model.Expense;
import com.example.Temple.model.Person;
import com.example.Temple.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expense")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<?> getAllExpense(){
        List<Expense> expenseList = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenseList, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getExpenseById(@RequestParam Long id){
        Optional<Expense> optionalExpense = expenseService.getExpenseById(id);
        if(optionalExpense.isPresent()){
            return new ResponseEntity<>(optionalExpense.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Id not found", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/owner")
    public ResponseEntity<?> getExpenseByOwnerId(@RequestParam Long id){
        try{
             Expense expense = expenseService.getExpenseByOwner(id);
             return new ResponseEntity<>(expense, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public  ResponseEntity<?> createExpense(@RequestBody Expense expense){
        try {
            System.out.println("Expense:"+expense);
            Expense savedExpense = expenseService.saveExpense(expense);
            return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
