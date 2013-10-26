package Serveur.Classes;

import java.util.Date;

public class Message {

	private int id;
	private String contenu;
	private Personne auteur;
	private Date date;
	
	public Message(int _id, String _contenu, Personne _auteur)
	{
		this.id 		= _id;
		this.contenu	= _contenu;
		this.auteur 	= _auteur;
		this.date 		= new Date(System.currentTimeMillis());
	}
	
	public int getId()
	{
		return this.id;
	}

	public String getContenu() {
		return this.contenu;
	}

	public void setContenu(String _contenu) {
		this.contenu = _contenu;
	}

	public Personne getAuteur() {
		return this.auteur;
	}

	public void setAuteur(Personne _auteur) {
		this.auteur = _auteur;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date _date) {
		this.date = _date;
	}
	
	@Override
	public boolean equals(Object _o)
	{
		if (_o instanceof Message)
		{
			Message m = (Message) _o;
			return m.getId() == this.getId();
		}
		
		return false;
	}

}
