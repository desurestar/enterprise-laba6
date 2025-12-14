package ru.zagrebin.laba11.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRecord {
    private String username;
    private String password;
    private String role;
}
