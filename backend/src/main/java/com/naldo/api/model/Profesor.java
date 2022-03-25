package com.naldo.api.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_profesor;

    @NotEmpty(message="no puede ser vacío")
    @Size(min = 4,max = 20,message = "debe tener entre 4 y 20 carácteres")
    @Column(name = "nombre",nullable = false)
    private String nombre;

    @NotEmpty(message="no puede estar vacio")
    @Email(message = "no es una dirección de correo con formato válido")
    @Column(name = "email",nullable = false)
    private String email;

    @NotNull(message = "no puede ser vacío")
    @Column(name = "experiencia",nullable = false)
    private Integer experiencia;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    public Profesor() {
    }

    public Integer getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Integer id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id_profesor=" + id_profesor +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", experiencia=" + experiencia +
                ", fecha_nacimiento=" + fecha_nacimiento +
                '}';
    }
}
