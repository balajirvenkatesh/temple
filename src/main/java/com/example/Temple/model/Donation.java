package com.example.Temple.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "donations")
@Data
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Transaction reference from Stripe/PayPal/Razorpay/UPI
    @Column(unique = true)
    private String transactionReference;

    @Column(nullable = false)
    private BigDecimal amount; // Use BigDecimal for financial accuracy

    @Column(length = 3)
    private String currency = "INR";

    @Enumerated(EnumType.STRING)
    private DonationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person donor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temple_id")
    private Temple temple;

    private String message; // Optional note from the donor

    private boolean anonymous = false;

    private LocalDateTime donatedAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> metadata;

    @PrePersist
    protected void onDonation() {
        this.donatedAt = LocalDateTime.now();
    }
}