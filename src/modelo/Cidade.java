package modelo;

import java.util.ArrayList;

public class Cidade {
  private String nome;

  ArrayList<Apresentacao> apresentacoes = new ArrayList<>();

public Cidade(String nome) {
	this.nome = nome;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public ArrayList<Apresentacao> getApresentacoes(){
	return apresentacoes;
}
@Override
public String toString() {
	return "Cidade [nome=" + nome + "]";
}
}