
package appconsole;

import modelo.Artista;
import regras_negocio.Fachada;

import static regras_negocio.Fachada.*;

public class Cadastrar {

	public Cadastrar() {
		try {
			inicializar();
			System.out.println("cadastrando artista...");
			cadastrarArtista("Fausto Ayres");
			cadastrarArtista("Arthur Lyra");
			cadastrarArtista("Brian Rafael");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando cidade...");
			cadastrarCidade("João Pessoa");
			cadastrarCidade("Sapé");
			cadastrarCidade("Campina Grande");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("cadastrando apresentacoes...");
			cadastrarApresentacao(1,"17/10/2023", "Fausto Ayres", "João Pessoa", 90);
			cadastrarApresentacao(2,"29/03/2024", "Arthur Lyra", "Sapé", 100);
			cadastrarApresentacao(3,"07/09/2023", "Brian Rafael", "Campina Grande", 200);
			cadastrarApresentacao(4,"10/11/2025", "Arthur Lyra", "Campina Grande", 150);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		finalizar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}
