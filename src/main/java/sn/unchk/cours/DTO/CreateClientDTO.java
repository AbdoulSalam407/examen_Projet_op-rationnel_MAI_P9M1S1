package sn.unchk.cours.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClientDTO(
        @NotBlank(message = "Le prénom est obligatoire")
        @Size(min = 2, max = 50)
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        @Size(min = 2, max = 50)
        String lastName,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "L'email doit être valide")
        String email
) {
}
