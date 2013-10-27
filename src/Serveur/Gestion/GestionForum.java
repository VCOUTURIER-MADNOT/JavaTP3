package Serveur.Gestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import Serveur.Classes.Forum;
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
	


	public boolean createXml(Forum _forum)
	{

		File f = new File(_forum.getURL());
		
		if(!f.exists())
		{	
			Document doc = new Document();
			Element root = new Element("Forum");
		
			doc.setRootElement(root);
			
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		    
		    try {
				sortie.output(doc, new FileOutputStream(_forum.getURL()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		    return true;
		}
		
		return false;		
	}
}
