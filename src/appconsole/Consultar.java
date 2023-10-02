

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


			System.out.println("\nlistar Apresentações na data 17/10/2023");
			for(Apresentacao a : Fachada.Listarapresentacoes("17/10/2023"))
				System.out.println(a);


			System.out.println("\nlistar Artistas que se apresentarão na cidade de Campina Grande");
			for(Artista apr : Fachada.apresentacaoCidade("Campina Grande"))
				System.out.println(apr);

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

