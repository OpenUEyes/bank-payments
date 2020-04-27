package com.company.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Deposit extends BaseEntity{

    private Double amount;
    private Double rate;
    private LocalDate start;
    private LocalDate finish;

    @Builder
    public Deposit(Long id, Double amount, Double rate, LocalDate start, LocalDate finish) {
        super(id);
        this.amount = amount;
        this.rate = rate;
        this.start = start;
        this.finish = finish;
    }
}