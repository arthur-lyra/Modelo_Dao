/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Artista;

public class DAOArtista extends DAO<Artista>{

	public Artista read (Object chave){
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Artista.class);
		q.descend("nome").constrain(nome);
		List<Artista> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	//--------------------------------------------
	//  consultas de Artista
	//--------------------------------------------

	//Listar o Artista com maior número de apresentações
	public List<Artista> ListarMaiorApresentacao() {
		Query q = manager.query();
		q.constrain(Artista.class);

		List<Artista> resultados = q.execute();
		int maxPresentations = 0;
		List<Artista> artistsWithMaxPresentations = new ArrayList<>();

		for (Artista artista : resultados) {
			int numPresentations = artista.getApresentacoes().size();
			if (numPresentations > maxPresentations) {
				maxPresentations = numPresentations;
				artistsWithMaxPresentations.clear();
				artistsWithMaxPresentations.add(artista);
			} else if (numPresentations == maxPresentations) {
				artistsWithMaxPresentations.add(artista);
			}
		}

		return artistsWithMaxPresentations;
	}


	//listar Artistas que se apresentarão na cidade de x
	public List<Artista> Apresentacaocidade(String n){
		Query q = manager.query();
		q.constrain(Artista.class);
		q.descend("apresentacoes").descend("cidade").descend("nome").constrain(n);
		return q.execute();
	}

	
}
