package com.company.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Credit extends BaseEntity {

    private Double debt;
    private Double limit;
    private Double percentage;
    private LocalDate start;
    private LocalDate deadline;

    @Builder
    public Credit(Long id, Double debt, Double limit, Double percentage, LocalDate start, LocalDate deadline) {
        super(id);
        this.debt = debt;
        this.limit = limit;
        this.percentage = percentage;
        this.start = start;
        this.deadline = deadline;
    }
}