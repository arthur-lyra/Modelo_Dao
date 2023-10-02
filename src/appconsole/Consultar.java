/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import modelo.Artista;
import modelo.Apresentacao;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {
			Fachada.inicializar();

			System.out.println("consultas... \n");
			System.out.println("\nListar Maior Apresentação");
			for(Artista a : Fachada.ListarMaiorApresentacao())
				System.out.println(a);


			System.out.println("\nalugueis finalizados");
			for(Apresentacao a : Fachada.Listarapresentacoes(modelo:"17/10/2023"))
				System.out.println(a);


			System.out.println("\ncarros que possuem 1 alugueis");
			for(Artista apr : Fachada.Apresentacaocidade(:"Campina Grande"))
				System.out.println(apr);


			//System.out.println("clientes que possuem 2 alugueis");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

