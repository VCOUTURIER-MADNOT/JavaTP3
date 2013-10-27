package Serveur.Gestion;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import Serveur.Classes.Personne;
import Serveur.Interfaces.IGestionUtilisateur;
import Serveur.NotifyLists.UtilisateurNotifyList;


public class GestionUtilisateur extends UnicastRemoteObject implements IGestionUtilisateur{

	static UtilisateurNotifyList personnes;
	
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
