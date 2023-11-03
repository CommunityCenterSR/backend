package com.inkua.communitycenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Size(min = 3, max = 100)
    @NotBlank
    private String title;

    @Size(max = 120)
    private String shortDescription;

    private String longDescription;

    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    private byte important = 0; // Destacado: 1=Sí / 0=No

    private String image;

    @ManyToOne
    private Category category;


}
