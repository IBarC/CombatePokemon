package mainapp;

import models.Entrenador;
import models.Equipo;
import models.Estado;
import models.Movimiento;
import models.Pokemon;
import models.TipoPokemon;

public class Main {

	public static void main(String[] args) {

		int contTurnos = 1;
		int contEnv = 0;
		int contDormido = 0;
		int contCong = 0;

		// Estados en los que pueden estar los pokemons
		Estado par = new Estado("Paralizado");
		Estado que = new Estado("Quemado");
		Estado env = new Estado("Envenenado");
		Estado dor = new Estado("Dormido");
		Estado con = new Estado("Congelado");
		Estado nin = new Estado("Ninguno");

		// Tipos de pokemons que existen
		TipoPokemon nor = new TipoPokemon("Nor");
		TipoPokemon luc = new TipoPokemon("Luc");
		TipoPokemon vol = new TipoPokemon("Vol");
		TipoPokemon ven = new TipoPokemon("Ven");
		TipoPokemon tie = new TipoPokemon("Tie");
		TipoPokemon roc = new TipoPokemon("Roc");
		TipoPokemon bic = new TipoPokemon("Bic");
		TipoPokemon nulo = new TipoPokemon("Nulo");

		// Movimientos que se pueden hacer
		Movimiento pla = new Movimiento("Placaje", nor, 35, 0, 0, 0, 0, 0, nin, 40);

		// Pokemons que se pueden tener
		Pokemon cat = new Pokemon(10, "Caterpie", bic, nulo, 30, 35, 20, 20, 45, null, 45, 1);
		cat.movimientos.add(pla);

		// Equipos que pueden haber
		Equipo equipo1 = new Equipo(null);
		equipo1.equipo.add(cat);

		// Entrenadores que se pueden enfrentar
		Entrenador e1 = new Entrenador("Irene", equipo1);

	}

}
