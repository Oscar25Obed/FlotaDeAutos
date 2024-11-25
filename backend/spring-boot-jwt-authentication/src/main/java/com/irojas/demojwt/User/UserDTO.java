package com.irojas.demojwt.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Schema(description = "ID único del usuario", example = "1")
    int id;

    @Schema(description = "Nombre de usuario", example = "johndoe", required = true)
    String username;

    @Schema(description = "Apellido del usuario", example = "Doe", required = true)
    String lastname;

    @Schema(description = "Nombre del usuario", example = "John")
    String firstname;

    @Schema(description = "País del usuario", example = "Panamá")
    String country;

}
