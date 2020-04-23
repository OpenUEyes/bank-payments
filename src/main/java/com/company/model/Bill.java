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

    private Type type;
    private Double balance;
    private LocalDate validity;

    @Builder
    public Bill(Long id, Type type, Double balance, LocalDate validity) {
        super(id);
        this.type = type;
        this.balance = balance;
        this.validity = validity;
    }
}