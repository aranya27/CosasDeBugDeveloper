package com.crud.beans;

public class Opcion {
	private String opcion;
	private String descripcion;
	
	public Opcion(String opcion, String descripcion){
		this.opcion=opcion;
		this.descripcion=descripcion;
	}
	
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
