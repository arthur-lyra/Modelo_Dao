package regras_negocio;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOArtista;
import daodb4o.DAOCidade;
import daodb4o.DAOApresentacao;
import daodb4o.DAOUsuario;
import modelo.Artista;
import modelo.Cidade;
import modelo.Apresentacao;
import modelo.Usuario;

public class Fachada {
	private Fachada() {}

	private static DAOArtista daoartista = new DAOArtista();  
	private static DAOApresentacao daoapresentacao = new DAOApresentacao(); 
	private static DAOCidade daocidade = new DAOCidade(); 
	private static DAOUsuario daousuario = new DAOUsuario(); 
	public static Usuario logado;	//contem o objeto Usuario logado em TelaLogin.java

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}


	public static Artista cadastrarArtista(String nome) throws Exception{
		DAO.begin();
		Artista artista = daoartista.read(nome);
		if (artista!=null)
			throw new Exception("Artista já cadastrado:" + nome);
		artista = new Artista(nome);

		daoartista.create(artista);
		DAO.commit();
		return artista;
	}

	public static Aluguel alugarCarro(String cpf, String placa, double diaria, String data1, String data2) throws Exception{
		DAO.begin();
		Carro car =  daoartista.read(placa);
		if(car==null) 
			throw new Exception ("carro incorreto para aluguel "+placa);
		if(car.isAlugado()) 
			throw new Exception ("carro ja esta alugado:"+placa);

		Cliente cli = daocidade.read(cpf);
		if(cli==null) 
			throw new Exception ("cliente incorreto para aluguel " + cpf);

		Aluguel aluguel = new Aluguel(data1,data2, diaria);
		aluguel.setCarro(car);
		aluguel.setCliente(cli);
		car.adicionar(aluguel);
		car.setAlugado(true);
		cli.adicionar(aluguel);

		daoapresentacao.create(aluguel);
		daoartista.update(car);
		daocidade.update(cli);
		DAO.commit();
		return aluguel;
	}

	public static void devolverCarro(String placa) throws Exception{
		DAO.begin();
		Carro car =  daoartista.read(placa);
		if(car==null) 
			throw new Exception ("carro incorreto para devolucao");

		if(car.getAlugueis().isEmpty()) 
			throw new Exception ("carro nao pode ser devolvido - nao esta alugado");

		car.setAlugado(false);
		// obter o ultimo aluguel do carro
		Aluguel alug = car.getAlugueis().get(car.getAlugueis().size()-1);
		alug.setFinalizado(true);

		daoartista.update(car);
		DAO.commit();
	}

	public static void excluirArtista(String nome) throws Exception{
		DAO.begin();
		Artista art =  daoartista.read(nome);
		if(art==null) 
			throw new Exception ("Artista incorreto para exclusao " + nome);

		//apagar carro e seus alugueis em cascata
		daoartista.delete(art);
		DAO.commit();
	}

	public static Apresentacao cadastrarApresentacao(Integer id, String cpf) throws Exception{
		DAO.begin();
		Apresentacao apr = daoapresentacao.read(id);
		if (apr!=null)
			throw new Exception("Pessoa ja cadastrado:" + cpf);
		apr = new Apresentacao(nome, cpf);

		daocidade.create(cli);
		DAO.commit();
		return cli;
	}
	public static void excluirCliente(String cpf) throws Exception{
		DAO.begin();
		Cliente cli =  daocidade.read(cpf);
		if(cli==null) 
			throw new Exception ("cliente incorreto para exclusao " + cpf);

		if(!cli.getAlugueis().isEmpty()) {
			List<Aluguel> alugueis = cli.getAlugueis();
			Aluguel ultimo = alugueis.get(alugueis.size()-1);
			if(ultimo !=null && !ultimo.isFinalizado()) 
				throw new Exception ("Nao pode excluir cliente com carro alugado: " + cpf);
		}
		
		//alterar os carros dos alugueis 
		for (Aluguel a : cli.getAlugueis()) {
			Carro car = a.getCarro();
			car.remover(a);
			daoartista.update(car);
			daoapresentacao.delete(a);
		}

		//apagar carro e seus alugueis em cascata
		daocidade.delete(cli);
		DAO.commit();
	}

	public static void excluirAluguel(int id) throws Exception{
		DAO.begin();
		Aluguel aluguel =  daoapresentacao.read(id);
		if(aluguel==null) 
			throw new Exception ("aluguel incorreto para exclusao " + id);

		if(! aluguel.isFinalizado()) 
			throw new Exception ("aluguel nao finalizado nao pode ser excluido " + id);

		//alterar os clientes dos alugueis do carro
		Cliente cli = aluguel.getCliente();
		Carro car = aluguel.getCarro();
		cli.remover(aluguel);
		car.remover(aluguel);

		daocidade.update(cli);
		daoartista.update(car);
		daoapresentacao.delete(aluguel);
		DAO.commit();
	}

	public static List<Artista>  listarArtistas(){
		DAO.begin();
		List<Artista> resultados =  daoartista.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Apresentacao>  listarApresentacao(){
		DAO.begin();
		List<Apresentacao> resultados =  daoapresentacao.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Cidade> listarCidades(){
		DAO.begin();
		List<Cidade> resultados =  daocidade.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Usuario>  listarUsuarios(){
		DAO.begin();
		List<Usuario> resultados =  daousuario.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Apresentacao> Listarapresentacoes(String modelo){	
		DAO.begin();
		List<Apresentacao> resultados =  daoapresentacao.Listarapresentacoes(modelo);
		DAO.commit();
		return resultados;
	}

	public static List<Artista> ListarMaiorApresentacao(){	
		DAO.begin();
		List<Artista> resultados =  daoartista.ListarMaiorApresentacao();
		DAO.commit();
		return resultados;
	}

	public static List<Artista>  Apresentacaocidade(String n){	
		DAO.begin();
		List<Artista> resultados =  daoartista.Apresentacaocidade(n);
		DAO.commit();
		return resultados;
	}

	public static Artista localizarArtista(String nome){
		return daoartista.read(nome);
	}
	public static Apresentacao localizarApresentacao(int id){
		return daoapresentacao.read(id);
	}

	
	//------------------Usuario------------------------------------
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu==null)
			return null;
		if (! usu.getSenha().equals(senha))
			return null;
		return usu;
	}
}
