package Serveur.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestionUtilisateur extends Remote {

	public void addPersonneList(String _nom, String _prenom, String _login, String _mdp) throws RemoteException;
	
	public void supprPersonneList(String _login) throws RemoteException;

}
