package com.company.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Bill extends BaseEntity {

    private String number;
    private Type type;
    private Double balance;
    private LocalDate validity;
    private Long accountId;

    @Builder
    public Bill(String number, Long id, Type type, Double balance, LocalDate validity, Long accountId) {
        super(id);
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.validity = validity;
        this.accountId = accountId;
    }
}