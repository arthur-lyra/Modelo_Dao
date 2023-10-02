/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Apresentacao;

public class DAOApresentacao  extends DAO<Apresentacao>{

	public Apresentacao read (Object chave){
		int id = (int) chave;	
		Query q = manager.query();
		q.constrain(Apresentacao.class);
		q.descend("id").constrain(id);
		List<Apresentacao> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	//metodo da classe DAO sobrescrito DAOAluguel para
	//criar "id" sequencial 
	public void create(Apresentacao obj){
		int novoid = super.gerarId();  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store( obj );
	}

	//--------------------------------------------
	//  consultas de Aluguel
	//--------------------------------------------

	//listar Apresentações na data x
	public List<Apresentacao> Listarapresentacoes(String modelo){
		Query q;
		q = manager.query();
		q.constrain(Apresentacao.class);
		q.descend("data").constrain(modelo);
		return q.execute();
	}

}
