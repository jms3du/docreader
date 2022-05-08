package main.java.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Estudiante implements Serializable{
	
	public static final String NOMBRE 						=		"nombre";
	public static final String APELLIDOS 					=		"apellidos";
	public static final String FECHA_NACIMIENTO 			=		"fechaNacimiento";
	public static final String GENERO 						=		"genero";
	public static final String ID 							=		"id";
	public static final String SEPARADOR_FECHA				=		"-";
	
	
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private Genero genero;
	private Integer id;
	
	public Estudiante() {
		super();
	}
	
	public Estudiante(Integer id, String nombre, String apellidos, String fechaNacimiento, Genero genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", genero=" + genero + "]";
	}
	
	
	
	

}
