package sn.unchk.cours.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderDTO(
        LocalDateTime orderDate,

        @NotNull(message = "Le client est obligatoire")
        Long clientId,

        @NotEmpty(message = "La commande doit contenir au moins un produit")
        List<@Valid CreateOrderDetailsDTO> orderDetails
) {
}
