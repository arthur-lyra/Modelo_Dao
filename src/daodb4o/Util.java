

package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

import modelo.Cidade;
import modelo.Apresentacao;
import modelo.Artista;

public class Util {
	private static ObjectContainer manager=null;

	
	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;		//ja tem uma conexao

		//---------------------------------------------------------------
		//configurar, criar e abrir banco local (pasta do projeto)
		//---------------------------------------------------------------
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata na altera��o, remo��o e leitura
		config.common().objectClass(Cidade.class).cascadeOnDelete(false);;
		config.common().objectClass(Cidade.class).cascadeOnUpdate(true);;
		config.common().objectClass(Cidade.class).cascadeOnActivate(true);
		config.common().objectClass(Apresentacao.class).cascadeOnDelete(false);;
		config.common().objectClass(Apresentacao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Apresentacao.class).cascadeOnActivate(true);
		config.common().objectClass(Artista.class).cascadeOnDelete(false);;
		config.common().objectClass(Artista.class).cascadeOnUpdate(true);;
		config.common().objectClass(Artista.class).cascadeOnActivate(true);
		
		//conexao local
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
	public static ObjectContainer conectarBancoRemoto(){		
		//---------------------------------------
		//configurar e conectar/criar banco remoto
		//---------------------------------------

		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);  // 0,1,2,3...

		// habilitar cascata na altera��o, remo��o e leitura
		config.common().objectClass(Cidade.class).cascadeOnDelete(false);;
		config.common().objectClass(Cidade.class).cascadeOnUpdate(true);;
		config.common().objectClass(Cidade.class).cascadeOnActivate(true);
		config.common().objectClass(Apresentacao.class).cascadeOnDelete(false);;
		config.common().objectClass(Apresentacao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Apresentacao.class).cascadeOnActivate(true);
		config.common().objectClass(Artista.class).cascadeOnDelete(false);;
		config.common().objectClass(Artista.class).cascadeOnUpdate(true);;
		config.common().objectClass(Artista.class).cascadeOnActivate(true);

		//Conex�o remota 
		//****************
		String ipservidor="";
		//ipservidor = "10.0.4.189";		// computador do professor (lab3)
		ipservidor = "54.163.92.174";		// AWS
		manager = Db4oClientServer.openClient(config, ipservidor, 34000,"usuario1","senha1");
		return manager;
	}

	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
}
