package Serveur.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IForum extends Remote {

	public void ecrireMessage(String _message) throws RemoteException;
	
	public String getMessages() throws RemoteException;
}
