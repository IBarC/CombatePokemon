package models;

public class Combate {
	
	public Entrenador entrenador1;
	public Entrenador entrenador2;
	public Pokemon pokemon1;
	public Pokemon pokemon2;
	
	public Combate(Entrenador entrenador1, Entrenador entrenador2, Pokemon pokemon1, Pokemon pokemon2) {
		super();
		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
	}

	public Entrenador getEntrenador1() {
		return entrenador1;
	}

	public void setEntrenador1(Entrenador entrenador1) {
		this.entrenador1 = entrenador1;
	}

	public Entrenador getEntrenador2() {
		return entrenador2;
	}

	public void setEntrenador2(Entrenador entrenador2) {
		this.entrenador2 = entrenador2;
	}

	public Pokemon getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
	}
	
	/**
	 * Establece el primer pokemon que ataca al inicar el combate
	 * 
	 * @return el primer pokemon en atacar
	 */
	public Pokemon quienEmpiezaTurno() {
		int random = (int)(Math.random()*2);
		
		if(random==0) {
			System.out.println("Empieza atacando " + this.entrenador1.nombre);
			return entrenador1.equipo.equipo.get(0);
		} else {
			System.out.println("Empieza atacando " + this.entrenador2.nombre);
			return entrenador2.equipo.equipo.get(0);
		}
		
	}
	
	public void aplicarMovimiento() {
		
	}
}
