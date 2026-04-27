package com.csuf.expensesplittingapi.controller;

import com.csuf.expensesplittingapi.dto.ExpenseDto;
import com.csuf.expensesplittingapi.model.Expense;
import com.csuf.expensesplittingapi.model.Group;
import com.csuf.expensesplittingapi.model.User;
import com.csuf.expensesplittingapi.repository.ExpenseRepository;
import com.csuf.expensesplittingapi.repository.GroupRepository;
import com.csuf.expensesplittingapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Helper method to get the ID of the user currently making the request
    private Long getAuthenticatedUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // 1. CREATE - Must return HTTP 201 Created [cite: 93, 101]
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto request) {
        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));

        Expense expense = new Expense(request.getDescription(), request.getAmount(), request.getDate(), user, group);
        Expense savedExpense = expenseRepository.save(expense);

        ExpenseDto responseDto = new ExpenseDto(savedExpense.getId(), savedExpense.getDescription(),
                savedExpense.getAmount(), savedExpense.getDate(), group.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 2. READ ALL - Must filter by authenticated user's ID
    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        Long userId = getAuthenticatedUserId();
        List<Expense> expenses = expenseRepository.findByUserId(userId);

        List<ExpenseDto> response = expenses.stream()
                .map(exp -> new ExpenseDto(exp.getId(), exp.getDescription(), exp.getAmount(), exp.getDate(), exp.getGroup().getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // 3. READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found")); // 404 if missing [cite: 93]

        if (!expense.getUser().getId().equals(getAuthenticatedUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied"); // 403 if it belongs to someone else [cite: 93]
        }

        ExpenseDto responseDto = new ExpenseDto(expense.getId(), expense.getDescription(),
                expense.getAmount(), expense.getDate(), expense.getGroup().getId());
        return ResponseEntity.ok(responseDto);
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found")); // 404 if missing [cite: 93]

        if (!expense.getUser().getId().equals(getAuthenticatedUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied"); // 403 if not the owner [cite: 93]
        }

        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());

        Expense savedExpense = expenseRepository.save(expense);
        ExpenseDto responseDto = new ExpenseDto(savedExpense.getId(), savedExpense.getDescription(),
                savedExpense.getAmount(), savedExpense.getDate(), savedExpense.getGroup().getId());

        return ResponseEntity.ok(responseDto);
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found")); // 404 if missing [cite: 93]

        if (!expense.getUser().getId().equals(getAuthenticatedUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied"); // 403 if not the owner [cite: 93]
        }

        expenseRepository.delete(expense);
        return ResponseEntity.ok().build();
    }
}