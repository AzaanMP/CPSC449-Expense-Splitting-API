package com.csuf.expensesplittingapi.repository;

import com.csuf.expensesplittingapi.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Required to satisfy the strict Data Isolation rule for the Read All endpoint
    List<Expense> findByUserId(Long userId);
}