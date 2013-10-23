package Serveur.Classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class Forum extends UnicastRemoteObject{
	
	private String sujet;
	private Map<String, String> message;
	
	public Forum(String _sujet) throws RemoteException
	{
		this.sujet = _sujet;
	}
	
	public String getSujet(){
		return this.sujet;
	}
	
	public String getURL()
	{
		return this.sujet.trim();
	}
	
}
