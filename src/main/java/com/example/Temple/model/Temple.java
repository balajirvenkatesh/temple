package com.example.Temple.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Table(name = "temples")
@Data
@NoArgsConstructor
public class Temple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mainDeity;

    private String architectureStyle; // e.g., "Dravidian", "Nagara"

    @Column(length = 100)
    private String city;

    private String state;

    private String country;

    @Lob // For long descriptions
    private String historyDescription;

    private Integer constructionYear;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private boolean isHeritageSite;

    private String websiteUrl;

    // Optional: Reference to a Category or Religion
    private String religion;
}