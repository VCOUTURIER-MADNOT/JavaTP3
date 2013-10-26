package Serveur.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Serveur.Classes.Personne;
import Serveur.NotifyLists.MessageNotifyList;

public interface IForum extends Remote {

	public void addMessage(String _message, Personne _auteur) throws RemoteException;
	
	public MessageNotifyList getMessages() throws RemoteException;
}
