package org.assignment1.assignment1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDTO {
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String name;

    private LocalDate dob;
    private String address;
    private String department;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\+62[0-9]{9,13}$", message = "Phone number must be in Indonesian format")
    private String nphone;
}
