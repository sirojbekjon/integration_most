package com.example.mib2_integrate.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    @Column(nullable = false)
    @NotBlank(message = "Transaction ID cannot be blank")
    private String transaction_id;

    @NotBlank(message = "sender_pnfl ID cannot be blank")
    @Column(nullable = false)
    private String sender_pinfl;

    @NotBlank(message = "purpose ID cannot be blank")
    @Column(nullable = false)
    private String purpose;

    @NotBlank(message = "consent cannot be blank")
    @Column(nullable = false)
    private String consent;

    @NotBlank(message = "pinfl cannot be blank")
    @Column(length = 14, nullable = false)
    private String pinfl;

}
