

package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Cidade;

public class DAOCidade extends DAO<Cidade>{

	public Cidade read (Object chave){
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Cidade.class);
		q.descend("nome").constrain(nome);
		List<Cidade> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
}

