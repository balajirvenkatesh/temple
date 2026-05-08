package com.example.Temple.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.PrintQuality;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String expenseReference; // e.g., "REQ-5521"

    @Column(nullable = false)
    private BigDecimal totalAmount;

    private BigDecimal taxAmount;

    @Column(length = 3)
    private String currency = "USD";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_category_id", nullable = false)
//    @JsonIgnore
    private ExpenseCategory category; // Enum: TRAVEL, MEALS, OFFICE_SUPPLIES, SOFTWARE

    @Column(nullable = false)
    private LocalDate dateOfExpenditure;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
//    @JsonIgnore
    private Person owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
//    @JsonIgnore
    private Person vendor;


    @Lob
    private String description;

    private String receiptAttachmentUrl;

    // Metadata for auditing
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}