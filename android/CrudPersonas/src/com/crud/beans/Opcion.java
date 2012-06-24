package com.crud.beans;

public class Opcion {
	private int id;
	private String opcion;
	private String descripcion;
	
	public Opcion(String opcion, String descripcion){
		this.opcion=opcion;
		this.descripcion=descripcion;
	}
	
	public Opcion(int id, String opcion, String descripcion){
		this.id=id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String toString(){
		return this.id+" - "+this.opcion+" - "+this.descripcion;
	}
	
}
