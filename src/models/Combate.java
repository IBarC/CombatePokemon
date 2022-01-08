package models;

public class Combate {

	public Entrenador entrenador1;
	public Entrenador entrenador2;
	public Pokemon pokemon1;
	public Pokemon pokemon2;

	/**
	 * Constructor de la clase Combate
	 * 
	 * @param entrenador1
	 * @param entrenador2
	 * @param pokemon1    pokemon elegido para el combate por el entrenador1
	 * @param pokemon2    pokemon elegido para el combate por el entrenador2
	 */
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
	public Pokemon quienEmpiezaTurno(int i) {

		if (pokemon1.speed >= pokemon2.speed) {
			return entrenador1.equipo.equipo.get(i);
		} else {
			return entrenador2.equipo.equipo.get(i);
		}

	}

	public void aplicarMovimiento(int contTurnos, int contEnv, int contDormido, int contCong) {

		if (pokemon1.speed >= pokemon2.speed) {
			Movimiento m = pokemon1.elegirMovimiento();

			boolean noMov = false;

			// Estados en los que se puede encontrar el pokemon atacante
			switch (pokemon1.estado.nombre) {
			case "Paralizado":
				int random = (int) (Math.random() * 4);
				if (random == 0) {
					System.out.println(pokemon1.nombre + " está paralizado y no se puede mover");
					noMov = true;
				}
				break;

			case "Quemado":
				System.out.println(pokemon1.nombre + " está quemado y pierde " + (pokemon1.actualHP / 16) + " PS");
				pokemon1.actualHP -= pokemon1.actualHP / 16;
				break;

			case "Envenenado":
				contEnv++;
				System.out.println(pokemon1.nombre + " está envenenado y pierde " + (pokemon1.actualHP / 8) + " PS");
				pokemon1.actualHP -= pokemon1.actualHP / 8;
				if (contTurnos % contEnv == 0) {
					pokemon1.actualHP -= 1;
				}
				break;

			case "Dormido":
				contDormido++;
				if (contDormido < 7) {
					System.out.println(pokemon1.nombre + " está dormido y no puede atacar");
					noMov = true;
				} else {
					pokemon1.estado.nombre = "Ninguno";
					System.out.println(pokemon1.nombre + " se ha despertado.");
				}
				break;

			case "Congelado":
				contCong++;
				if (contCong < 3) {
					System.out.println(pokemon1.nombre + " está congelado y no puede atacar");
					noMov = true;
				} else {
					pokemon1.estado.nombre = "Ninguno";
					System.out.println(pokemon1.nombre + " se ha descongelado");
				}
			}

			// Si no ha salido la probabilidad de que al estar paralizado no ataque puede
			// realizar su movimiento
			if (!noMov) {
				if (m.power > 0) {
					double efecT1 = m.getEfectividad(pokemon2.tipo1);
					double efecT2 = m.getEfectividad(pokemon2.tipo2);

					double danio = 0.5 * (pokemon1.attack / pokemon1.defense) * m.actualPP;

					if (m.nombre.equalsIgnoreCase(pokemon1.tipo1.nombre)
							|| (m.nombre.equalsIgnoreCase(pokemon1.tipo2.nombre))) {
						danio = danio * 1.25;
					}

					if (efecT1 >= efecT2) {
						danio *= efecT1;
					} else {
						danio *= efecT2;
					}

					m.damage = (int) (danio / 10) + 1;

					pokemon2.actualHP -= m.damage;

					System.out.println("Le ha quitado " + m.damage + " PS");
				}

				// Todos los cambios que puede realizar el movimiento
				if (m.cambiaAttack != 0) {
					System.out
							.println("¡El ataque de " + pokemon2.nombre + " ha disminuido en " + m.cambiaAttack + "!");
					pokemon2.attack += m.cambiaAttack;
				}
				if (m.cambiaDef != 0) {
					System.out.println("¡La defensa de " + pokemon2.nombre + " ha disminuido en " + m.cambiaDef + "!");
					pokemon2.defense += m.cambiaDef;
				}
				if (m.cambiaSpAttack != 0) {
					System.out.println("¡El ataque especial de " + pokemon2.nombre + " ha disminuido en "
							+ m.cambiaSpAttack + "!");
					pokemon2.specialAttack += m.cambiaSpAttack;
				}
				if (m.cambiaSpDef != 0) {
					System.out.println(
							"¡La defensa especial de " + pokemon2.nombre + " ha disminuido en " + m.cambiaSpDef + "!");
					pokemon2.specialDefense += m.cambiaSpDef;
				}
				if (m.cambiaSpeed != 0) {
					System.out.println(
							"¡La velocidad de " + pokemon2.nombre + " ha disminuido en " + m.cambiaSpeed + "!");
					pokemon2.speed += m.cambiaSpeed;
				}

				// Estado que se le puede aplicar con el movimiento al pokemon atacado
				switch (m.aplicaEstado.nombre) {
				case "Paraliza":
					pokemon2.speed -= pokemon2.speed * 0.75;
					System.out.println(
							pokemon2.nombre + " ha sido paralizado y su velocidad se ha reducido a " + pokemon2.speed);
					pokemon2.estado.nombre="Paralizado";
					break;
				case "Quema":
					pokemon2.attack /= 2;
					System.out.println("¡" + pokemon2.nombre + " se ha quemado y su ataque se ha reducido a la mitad!");
					pokemon2.estado.nombre="Quemado";
					break;
				case "Envenena":
					System.out.println("¡" + pokemon2.nombre + " ha sido envenenado!");
					pokemon2.estado.nombre="Envenenado";
					break;
				case "Duerme":
					System.out.println("¡Oh no, " + pokemon2.nombre + " se ha dormido!");
					pokemon2.estado.nombre="Dormido";
					break;
				case "Congela":
					System.out.println("¡" + pokemon2.nombre + " ha sido congelado!");
					pokemon2.estado.nombre="Congelado";
					break;
				}
			}

		} else {
			Movimiento m = pokemon2.elegirMovimiento();
			boolean noMov = false;
			// Estados en los que se puede encontrar el pokemon atacante
			switch (pokemon1.estado.nombre) {
			case "Paralizado":
				int random = (int) (Math.random() * 4);
				if (random == 0) {
					System.out.println(pokemon2.nombre + " está paralizado y no se puede mover");
					noMov = true;
				}
				break;

			case "Quemado":
				System.out.println(pokemon2.nombre + " está quemado y pierde " + (pokemon2.actualHP / 16) + " PS");
				pokemon2.actualHP -= pokemon2.actualHP / 16;
				break;

			case "Envenenado":
				contEnv++;
				System.out.println(pokemon2.nombre + " está envenenado y pierde " + (pokemon2.actualHP / 8) + " PS");
				pokemon2.actualHP -= pokemon1.actualHP / 8;
				if (contTurnos % contEnv == 0) {
					pokemon2.actualHP -= 1;
				}
				break;

			case "Dormido":
				contDormido++;
				if (contDormido < 7) {
					System.out.println(pokemon2.nombre + " está dormido y no puede atacar");
					noMov = true;
				} else {
					pokemon1.estado.nombre = "Ninguno";
					System.out.println(pokemon2.nombre + " se ha despertado.");
				}
				break;

			case "Congelado":
				contCong++;
				if (contCong < 3) {
					System.out.println(pokemon2.nombre + " está congelado y no puede atacar");
					noMov = true;
				} else {
					pokemon2.estado.nombre = "Ninguno";
					System.out.println(pokemon1.nombre + " se ha descongelado");
				}
			}

			// Si no ha salido la probabilidad de que al estar paralizado no ataque puede
			// realizar su movimiento
			if (!noMov) {
				if (m.power > 0) {
					double efecT1 = m.getEfectividad(pokemon1.tipo1);
					double efecT2 = m.getEfectividad(pokemon1.tipo2);

					double danio = 0.5 * (pokemon2.attack / pokemon1.defense) * m.actualPP;

					if (m.nombre.equalsIgnoreCase(pokemon2.tipo1.nombre)
							|| (m.nombre.equalsIgnoreCase(pokemon2.tipo2.nombre))) {
						danio = danio * 1.25;
					}

					if (efecT1 >= efecT2) {
						danio *= efecT1;
					} else {
						danio *= efecT2;
					}

					m.damage = (int) (danio / 10) + 1;

					pokemon2.actualHP -= m.damage;

					System.out.println("Le ha quitado " + m.damage + " PS");
				}

				// Todos los cambios que puede realizar el movimiento
				if (m.cambiaAttack != 0) {
					System.out
							.println("¡El ataque de " + pokemon1.nombre + " ha disminuido en " + m.cambiaAttack + "!");
					pokemon2.attack += m.cambiaAttack;
				}
				if (m.cambiaDef != 0) {
					System.out.println("¡La defensa de " + pokemon1.nombre + " ha disminuido en " + m.cambiaDef + "!");
					pokemon2.defense += m.cambiaDef;
				}
				if (m.cambiaSpAttack != 0) {
					System.out.println("¡El ataque especial de " + pokemon1.nombre + " ha disminuido en "
							+ m.cambiaSpAttack + "!");
					pokemon2.specialAttack += m.cambiaSpAttack;
				}
				if (m.cambiaSpDef != 0) {
					System.out.println(
							"¡La defensa especial de " + pokemon1.nombre + " ha disminuido en " + m.cambiaSpDef + "!");
					pokemon2.specialDefense += m.cambiaSpDef;
				}
				if (m.cambiaSpeed != 0) {
					System.out.println(
							"¡La velocidad de " + pokemon1.nombre + " ha disminuido en " + m.cambiaSpeed + "!");
					pokemon2.speed += m.cambiaSpeed;
				}

				// Estado que se le puede aplicar con el movimiento al pokemon atacado
				switch (m.aplicaEstado.nombre) {
				case "Paraliza":
					pokemon1.speed -= pokemon2.speed * 0.75;
					System.out.println(
							pokemon1.nombre + " ha sido paralizado y su velocidad se ha reducido a " + pokemon1.speed);
					pokemon1.estado.nombre="Paralizado";
					break;
				case "Quema":
					pokemon2.attack /= 2;
					System.out.println("¡" + pokemon1.nombre + " se ha quemado y su ataque se ha reducido a la mitad!");
					pokemon1.estado.nombre="Quemado";
					break;
				case "Envenena":
					System.out.println("¡" + pokemon1.nombre + " ha sido envenenado!");
					pokemon1.estado.nombre="Envenenado";
					break;
				case "Duerme":
					System.out.println("¡Oh no, " + pokemon1.nombre + " se ha dormido!");
					pokemon1.estado.nombre="Dormido";
					break;
				case "Congela":
					System.out.println("¡" + pokemon1.nombre + " ha sido congelado!");
					pokemon1.estado.nombre="Congelado";
				}
			}
		}
	}

	public boolean isFinished() {
		boolean termina = false;
		for (Pokemon p : entrenador1.equipo.equipo) {
			if (p.actualHP != 0) {
				termina = false;
				break;
			} else {
				termina = true;
			}
		}
		if (termina) {
			System.out.println("Todos los pokemon de " + entrenador1.nombre + " se han debilitado.\nEl ganador es "
					+ entrenador2.nombre);
		} else {
			for (Pokemon p : entrenador2.equipo.equipo) {
				if (p.actualHP != 0) {
					termina = false;
					break;
				} else {
					termina = true;
				}
			}
			if (termina) {
				System.out.println("Todos los pokemon de " + entrenador2.nombre + " se han debilitado.\nEl ganador es "
						+ entrenador1.nombre);
			}
		}

		return termina;
	}
}
