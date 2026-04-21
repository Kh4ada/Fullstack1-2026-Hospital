package com.hospitalVM.atenciones.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(unique = true, nullable = false, length = 13)
    @NotBlank(message = "El campo run no puede ser vacio")
    @Pattern(regexp = "^\\d{7,8}-[\\dkK]$", message = "El formato del run debe ser xxxxxxxx-x")
    private String run;

    @Column(nullable = false)
    @NotBlank(message = "El campo nombres no puede ser vacio")
    private String nombres;

    @Column(nullable = false)
    @NotBlank(message = "El campo apellidos no puede ser vacio")
    private String apellidos;

    @NotNull(message = "El campo de fecha de nacimiento no puede ser vacio")
    @Column(name = "fecha_nacimiento", nullable = true)
    private Date fechaNacimiento;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El campo correo no puede ser vacio")
    @Email(message = "El correo no tiene un formato valido")
    private String correo;

    @Embedded
    Audit audit = new Audit();
}
