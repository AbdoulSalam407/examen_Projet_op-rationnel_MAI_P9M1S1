package sn.unchk.cours.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderDetailsDTO(
        @NotNull(message = "Le produit est obligatoire")
        Long productId,

        @NotNull(message = "Le prix est obligatoire")
        @Positive(message = "Le prix doit être supérieur à zéro")
        Double price,

        @NotNull(message = "La quantité est obligatoire")
        @Positive(message = "La quantité doit être supérieure à zéro")
        Integer quantity
) {
}
