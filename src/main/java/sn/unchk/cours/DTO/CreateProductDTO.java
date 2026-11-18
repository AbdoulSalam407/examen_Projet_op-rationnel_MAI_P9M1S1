package sn.unchk.cours.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateProductDTO(
        @NotBlank(message = "Le nom est obligatoire")
        @Size(min = 2, max = 100)
        String name,

        @Size(max = 255)
        String description,

        @NotNull(message = "Le prix est obligatoire")
        @Positive(message = "Le prix doit être supérieur à zéro")
        Double price,

        @NotNull(message = "Le stock est obligatoire")
        @PositiveOrZero(message = "Le stock ne peut pas être négatif")
        Integer stock
) {
}
