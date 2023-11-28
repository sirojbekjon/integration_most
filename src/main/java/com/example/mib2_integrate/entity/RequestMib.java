package com.example.mib2_integrate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mib2_requester")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestMib {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonIgnore
    @Column(updatable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    private String transaction_id;

    private String sender_pnfl;

    private String purpose;

    private String consent;

    @Column(length = 14)
    private String pinfl;

    private boolean status=false;


    public RequestMib(String transaction_id, String sender_pnfl, String purpose, String consent, String pinfl) {
        this.transaction_id = transaction_id;
        this.sender_pnfl = sender_pnfl;
        this.purpose = purpose;
        this.consent = consent;
        this.pinfl = pinfl;
    }
}
