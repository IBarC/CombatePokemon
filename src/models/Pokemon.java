package models;

import java.util.ArrayList;

public class Pokemon {

	public int numero;
	public String nombre;
	public TipoPokemon tipo1;
	public TipoPokemon tipo2;
	public Estado estado;
	public int attack;
	public int defense;
	public int specialAttack;
	public int specialDefense;
	public int speed;
	ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
	public int maxHP;
	public int actualHP;
	public int level;

	/**
	 * Constructor de la clase Pokemon
	 * 
	 * @param numero
	 * @param nombre
	 * @param tipo1
	 * @param tipo2
	 * @param estado
	 * @param attack
	 * @param defense
	 * @param specialAttack
	 * @param specialDefense
	 * @param speed
	 * @param movimientos
	 * @param maxHP
	 * @param actualHP
	 * @param level
	 */
	Pokemon(int numero, String nombre, TipoPokemon tipo1, TipoPokemon tipo2, Estado estado, int attack, int defense,
			int specialAttack, int specialDefense, int speed, ArrayList<Movimiento> movimientos, int maxHP,
			int actualHP, int level) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.estado = estado;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.movimientos = movimientos;
		this.maxHP = maxHP;
		this.actualHP = actualHP;
		this.level = level;
	}

	// Getters y setters

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoPokemon getTipo1() {
		return tipo1;
	}

	public void setTipo1(TipoPokemon tipo1) {
		this.tipo1 = tipo1;
	}

	public TipoPokemon getTipo2() {
		return tipo2;
	}

	public void setTipo2(TipoPokemon tipo2) {
		this.tipo2 = tipo2;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	public int getSpecialDefense() {
		return specialDefense;
	}

	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getActualHP() {
		return actualHP;
	}

	public void setActualHP(int actualHP) {
		this.actualHP = actualHP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
