package Serveur.Gestion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Serveur.Interfaces.IGestionForum;

public class GestionForum extends UnicastRemoteObject implements IGestionForum {

	protected GestionForum() throws RemoteException {
		super();
		
		
	}

	@Override
	public void creerForum(String _nom) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerForum(String _nom) throws RemoteException {
		// TODO Auto-generated method stub

	}
	
}
