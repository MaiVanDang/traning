package com.practice.entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="users")
public class UserEntity {
    private int id;
    private String name;
    private String email;

    // Constructor, Getter, Setter
}
