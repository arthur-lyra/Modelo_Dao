package modelo;

import java.util.ArrayList;

public class Apresentacao {
	private int id;
	private String data;
	private Artista artista;
	private Cidade cidade;
	private int precoIngresso;
	private ArrayList<Artista> artistas = new ArrayList<>();
	
	public Apresentacao(int id, String data , Artista artista, Cidade cidade, int precoIngresso) {
		this.id = id;
		this.data = data;
		this.artista = artista;
		this.cidade = cidade;
		this.precoIngresso = precoIngresso;
	}
	public void remover(Artista ar) {
	    ar.getApresentacoes().remove(this); // Remove esta apresentação da lista de apresentações do artista
	    this.artistas.remove(ar); // Remove o artista desta apresentação

	    if (ar.getApresentacoes().isEmpty()) {
	        ar.setApresentacoes(null); // Define como null se o artista não tiver mais apresentações
	    }
	}
	
	public ArrayList<Artista> getArtistas() {
		return artistas;
	}
	public void setArtistas(ArrayList<Artista> artistas) {
		this.artistas = artistas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public int getPrecoIngresso() {
		return precoIngresso;
	}
	public void setPrecoIngresso(int precoIngresso) {
		this.precoIngresso = precoIngresso;
	}
	@Override
	public String toString() {
		return "Apresentacao [id=" + id + ", data=" + data + ", artista=" + artista + ", cidade=" + cidade
				+ ", precoIngresso=" + precoIngresso + "]";
	}
}