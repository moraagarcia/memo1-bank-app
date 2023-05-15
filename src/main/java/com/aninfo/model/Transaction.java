package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long cbu;
    private TransactionType type;
    private Double sum;
    public Long getCbu() {
        return cbu;
    }

    public Transaction(Long cbu, Double sum, TransactionType transactionType) {
        this.cbu = cbu;
        this.sum = sum;
        this.type = transactionType;
    }

    public Transaction() {

    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionType getType() {
        return this.type;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
