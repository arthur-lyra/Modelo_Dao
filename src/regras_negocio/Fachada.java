package regras_negocio;

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

	public static Apresentacao cadastrarApresentacao(int id, String data, Artista artista, Cidade cidade, int precoIngresso) throws Exception{
		DAO.begin();
		Artista art =  daoartista.read(artista);
			if(art==null)
				throw new Exception ("Artista "+ artista + "não existe");

		Apresentacao ap = daoapresentacao.read(id);
			if(ap!=null)
				throw new Exception("Apresentacao ja cadastrada para o artista" + artista);

		Cidade cid = daocidade.read(cidade);
			if(cid==null)
				throw new Exception("Cidade " + cidade + "nao existe");

		Apresentacao apresentacao =  new Apresentacao(id,data,art,cidade,precoIngresso);
		art.adicionar(apresentacao);
		daoapresentacao.create(apresentacao);
		daoartista.update(art);
		DAO.commit();
		return apresentacao;
	}

	public Cidade cadastrarCidade(String nome) throws Exception{

		DAO.begin();

		Cidade cidade = daocidade.read(nome);

		if(cidade!=null)
			throw new Exception("A cidade" + nome + "ja está cadastrada");

		Cidade cid = new Cidade(nome);
		daocidade.create(cid);
		DAO.commit();
		return cid;
	}

	public static void excluirArtista(String nome) throws Exception{
		DAO.begin();
		Artista art =  daoartista.read(nome);
		if(art==null) 
			throw new Exception ("Artista " + nome + " incorreto para exclusao");

		//apagar artista e suas apresentacoes em cascata
		daoartista.delete(art);
		DAO.commit();
	}

	public static void excluirApresentacao(int id) throws Exception{
		DAO.begin();
		Apresentacao ap = daoapresentacao.read(id);

		if(ap==null)
			throw new Exception("Id:" + id + "incorreto para exclusão da apresentacao");

		//apagar apresentacao e seus artistas em cascata
		daoapresentacao.delete(ap);
		DAO.commit();
	}

	public static void excluirCidade(String nome) throws Exception{
		DAO.begin();
		Cidade cidade = daocidade.read(nome);

		if(cidade==null)
			throw new Exception("Cidade " + nome + "incorreta para exclusão");

		Artista art = daoartista.read(art);
		for (Apresentacao a : art.getApresentacoes()){

		}
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
