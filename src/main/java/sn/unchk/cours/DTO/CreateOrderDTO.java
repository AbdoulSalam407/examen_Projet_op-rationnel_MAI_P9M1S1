package sn.unchk.cours.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateOrderDTO(
        LocalDateTime orderDate,

        @NotNull(message = "Le montant est obligatoire")
        @Positive(message = "Le montant doit être supérieur à zéro")
        Double totalAmount,

        @NotNull(message = "La quantité est obligatoire")
        @Positive(message = "La quantité doit être supérieure à zéro")
        Integer quantity
) {
}
