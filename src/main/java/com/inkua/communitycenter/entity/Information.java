package com.inkua.communitycenter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String type;
    private String content;
}
