package Serveur.Classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Serveur.Interfaces.IForum;
import Serveur.NotifyLists.MessageNotifyList;

public class Forum extends UnicastRemoteObject implements IForum{
	
	private String sujet;
	private MessageNotifyList messages;
	private int nbMessages;
	
	public Forum(String _sujet) throws RemoteException
	{
		this.sujet = _sujet;
		this.nbMessages = 0;
	}
	
	public String getSujet(){
		return this.sujet;
	}
	
	public String getURL()
	{
		return this.sujet.trim();
	}
	
	public MessageNotifyList getMessages()
	{
		return this.messages;
	}
	
	@Override
	public void addMessage(String _contenu, Personne _auteur)
	{
		Message message = new Message( ++nbMessages, _contenu, _auteur);
		this.messages.add(message);
	}
	
	@Override
	public boolean equals(Object _o)
	{
		if (_o instanceof Forum)
		{
			Forum f = (Forum) _o;
			return f.getSujet().equals(this.getSujet());
		}
		
		return false;
	}

	
}
