package org.java.app.api.dto;

public class PizzaDTO {

	private int id;

	private String nome;

	private String descrizione;

	private int prezzo;

	private String foto;

	public PizzaDTO() {}
	
	public PizzaDTO(String nome) {
		
		setNome(nome);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}


	@Override
	public String toString() {

		return "[" + getId() + "] pizza:\n" + "nome: " + getNome() + "\n" + "descrizione: " + getDescrizione() + "\n"
				+ "foto URL: " + getFoto() + "\n" + "prezzo: " + getPrezzo() + "Euro";
	}

}
