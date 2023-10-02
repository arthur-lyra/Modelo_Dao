/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando artista...");
			Fachada.cadastrarArtista("Fausto Ayres");
			Fachada.cadastrarArtista("Arthur Lyra");
			Fachada.cadastrarArtista("Brian Rafael");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando cidade...");
			Fachada.cadastrarCidade("João Pessoa");
			Fachada.cadastrarCidade("Sapé");
			Fachada.cadastrarCidade("Campina Grande");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("cadastrando apresentacoes...");
			Fachada.cadastrarApresentacao(1,"17/10/2023", "Fausto Ayres" , "01/05/2022", "10/05/2022");
			Fachada.cadastrarApresentacao(2,"29/03/2024",200.0 , "01/05/2022", "10/05/2022");
			Fachada.cadastrarApresentacao(3,"07/09/2023",300.0 , "01/05/2022", "10/05/2022");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}
