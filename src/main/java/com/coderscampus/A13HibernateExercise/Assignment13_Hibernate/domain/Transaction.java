package com.coderscampus.A13HibernateExercise.Assignment13_Hibernate.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionsId;
    private LocalDateTime transactionDate;
    private Double amount;
    private String type;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public Long getTransactionsId() {
        return transactionsId;
    }

    public void setTransactionsId(Long transactionsId) {
        this.transactionsId = transactionsId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
