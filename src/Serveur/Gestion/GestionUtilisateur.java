package Serveur.Gestion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Serveur.Classes.Personne;
import Serveur.Interfaces.IGestionUtilisateur;
import Serveur.NotifyLists.UtilisateurNotifyList;


public class GestionUtilisateur extends UnicastRemoteObject implements IGestionUtilisateur{

	/**
	 * 
	 */
	private static final long serialVersionUID = 852404754652614214L;
	
	private static UtilisateurNotifyList personnes;
	
	public GestionUtilisateur() throws RemoteException
	{
		super();
		personnes = new UtilisateurNotifyList();
	}
	
	public void addPersonneList(String _nom, String _prenom, String _login, String _mdp) throws RemoteException
	{
		personnes.add(new Personne(_nom, _prenom, _login, _mdp));
	}
	
	public void supprPersonneList(String _login) throws RemoteException
	{
		Personne p;
		if((p = getPersonne(_login)) != null)
		{
			personnes.remove(p);
		}
	}
	
	public static Personne getPersonne(String _login)
	{
		int i = 0;
		Personne p = null;
		while(i < personnes.size() && p == null)
		{
			if (personnes.get(i).getLogin().equals(_login))
			{
				System.out.println(i);
				p = personnes.get(i);
			}
			i++;
		}
		
		return p;
	}
}
