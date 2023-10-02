/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import daodb4o.Util;
import modelo.Apresentacao;
import modelo.Artista;
import modelo.Cidade;
import modelo.Usuario;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de artistas:");
			for(Artista a: Fachada.listarArtistas())
				System.out.println(a);

			System.out.println("\n---listagem de cidades:");
			for(Cidade c: Fachada.listarCidades())
				System.out.println(c);
			
			System.out.println("\n---listagem de apresentacoes:");
			for(Apresentacao a: Fachada.listarApresentacao())
				System.out.println(a);

			System.out.println("\n---listagem de usuarios:");
			for(Usuario u: Fachada.listarUsuarios())
				System.out.println(u);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
