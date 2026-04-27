package com.csuf.expensesplittingapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseDto {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    private Long groupId; // We only send the Group's ID, not the whole nested Group object

    public ExpenseDto(Long id, String description, BigDecimal amount, LocalDateTime date, Long groupId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.groupId = groupId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public Long getGroupId() { return groupId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
}