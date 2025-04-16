package com.example.edutrack.Moduls;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "estudiantes")
public class Estudiante {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "edad")
    private int edad;

    @ColumnInfo(name = "curso")
    private String curso;

    @ColumnInfo(name = "notaFinal")
    private double notaFinal;

    @ColumnInfo(name = "estado")
    private String estado; // Se genera automáticamente: Aprobado o Reprobado

    public Estudiante(String nombre, int edad, String curso, double notaFinal, String estado) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
        this.notaFinal = notaFinal;
        this.estado = estado;
    }

    // Constructor vacío
    public Estudiante() {
    }
    //Getters y setters

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }
}
