package models;

import java.util.ArrayList;

public class Equipo {

	ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

	/**
	 * Constructor de la clase equipo
	 * 
	 * @param equipo
	 */
	public Equipo(ArrayList<Pokemon> equipo) {
		super();
		this.equipo = equipo;
	}

	/**
	 * Devuelve el equipo pokemon
	 * 
	 * @return
	 */
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}
}
