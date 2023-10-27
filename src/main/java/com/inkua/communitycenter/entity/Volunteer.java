package com.inkua.communitycenter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "volunteers")
public class Volunteer {
    
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "volunteer_id")
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String message;
}
