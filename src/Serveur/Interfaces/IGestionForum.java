package Serveur.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestionForum extends Remote {
		
	public void creerForum(String _nom) throws RemoteException;
	
	public void supprimerForum(String _nom) throws RemoteException;
}
