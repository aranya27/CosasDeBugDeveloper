package com.example.asteroides;

import java.util.Vector;

public class AlmacenPuntuacionesArray implements AlmacenPuntuaciones {
	public Vector<String> puntuaciones;
	
	public AlmacenPuntuacionesArray(){
		puntuaciones = new Vector<String>();
		puntuaciones.add("123 pedrito");
		puntuaciones.add("123 pedrito");
		puntuaciones.add("123 pedrito");
		puntuaciones.add("123 pedrito");
		
	}
	
	@Override
	public void guardarPuntuacion(int puntos, String nombre, long fecha) {
		puntuaciones.add(0,puntos + " " + nombre);
		
	}

	@Override
	public Vector<String> listaPuntuaciones(int cantidad) {
		return puntuaciones;
	}

}
