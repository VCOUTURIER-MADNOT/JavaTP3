package Serveur.Gestion;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Serveur.ServeurRMI;
import Serveur.Classes.Forum;
import Serveur.Interfaces.IGestionForum;
import Serveur.NotifyLists.ForumNotifyList;

public class GestionForum extends UnicastRemoteObject implements IGestionForum {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6416503218399391747L;
	
	private static ForumNotifyList forums;
	
	public GestionForum() throws RemoteException {
		super();
		GestionForum.forums = new ForumNotifyList();
		for (Forum f : GestionForum.forums)
		{
			if (f != null)
			{
				try {
					ServeurRMI.registry.bind(f.getURL(), f);
				} catch (AlreadyBoundException e) {
					System.out.println("Naming déjà existant :" + e);
				}
			}
		}
	}

	@Override
	public void creerForum(String _nom) throws RemoteException {
		Forum f = new Forum(_nom);
		GestionForum.forums.add(f);
		try {
			ServeurRMI.registry.bind(f.getURL(), f);
		} catch (AlreadyBoundException e) {
			System.out.println("Naming déjà existant :" + e);
		}
	}

	@Override
	public void supprimerForum(String _nom) throws RemoteException {
		Forum f = getForum(_nom);
		if (f != null)
		{
			GestionForum.forums.remove(f);
			File file = new File(f.getURL() + ".xml");
			if(file != null)
			{
				file.delete();
			}
			
		}
		else
		{
			System.out.println("Le forum \"" + _nom + "\" n'existe pas.");
		}

	}

	private static Forum getForum(String _sujet)
	{
		int i = 0;
		Forum f = null;
		while(i < GestionForum.forums.size() && f == null)
		{
			if (GestionForum.forums.get(i).getSujet().equals(_sujet))
			{
				f = GestionForum.forums.get(i);
			}
			++i;
		}
		return f;
	}
}
