package com.company.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity {

    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;

    @Builder
    public Account(Long id, String login, String password, String email, String name, String surname, String phoneNumber) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}