package com.irojas.demojwt.User;

import com.irojas.demojwt.Rental.Rental;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/admin/user")
@CrossOrigin(origins = {"http://localhost:4200"})
@Tag(name = "User Controller", description = "Gestión de vehículos")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    @Operation(
            summary = "Obtener un usuario por ID",
            description = "Recupera la información del usuario basándose en el ID proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    public ResponseEntity<UserDTO> getUser(
            @Parameter(description = "ID del usuario a recuperar") @PathVariable Integer id
    ) {
        UserDTO userDTO = userService.getUser(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping()
    @Operation(
            summary = "Actualizar un usuario",
            description = "Actualiza los detalles del usuario basado en la información proporcionada en la solicitud.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Actualizacion de usuario",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
            }
    )
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "Datos del usuario a actualizar") @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }
}
