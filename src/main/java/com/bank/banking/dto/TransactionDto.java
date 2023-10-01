package com.bank.banking.dto;

import com.bank.banking.models.Address;
import com.bank.banking.models.Transaction;
import com.bank.banking.models.TransactionType;
import com.bank.banking.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id;

    @Positive
    @Max(value = 10000000)
    @Min(value = 10)
    private BigDecimal amount;

    private TransactionType type;

    private String destinationIban;

    private Integer userId;

    public static TransactionDto fromEntity(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .userId(transaction.getUser().getId())
                .build();
    }
    public static Transaction toEntity(TransactionDto transaction) {
        return Transaction.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .user(
                        User.builder()
                                .id(transaction.getUserId())
                                .build()
                )
                .build();
    }
}
