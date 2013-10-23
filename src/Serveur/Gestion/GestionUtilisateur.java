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


public class GestionUtilisateur extends UnicastRemoteObject implements IGestionUtilisateur{

	static Document document;
	static Element racine;
	static ArrayList<Personne> personnes;
	
	public GestionUtilisateur() throws RemoteException
	{
		super();
		
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File("Personnes.xml"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		racine = document.getRootElement();
		personnes = new ArrayList<Personne>();
		getPersonnes();
	}
	
	private static void getPersonnes() throws RemoteException
	{
		for(Element e: racine.getChildren())
		{
			Personne p = elementToPersonne(e);
			personnes.add(p);
		}
	}
	
	public void addPersonneList(String _nom, String _prenom, String _login, String _mdp) throws RemoteException
	{
		racine = document.getRootElement();
		personnes.clear();
		getPersonnes();
		
		Personne p = new Personne(_nom, _prenom, _login, _mdp);
		personnes.add(p);
		System.out.println("Ajout!");
		addPersonneXML(p);
	}
	
	public void supprPersonneList(String _login) throws RemoteException
	{
		Personne p;
		if((p = getPersonne(_login)) != null)
		{
			personnes.remove(p);
			supprPersonneXML(p);
		}
	}
	
	private static Personne getPersonne(String _login) throws RemoteException
	{
		for(Personne p : personnes)
		{
			if(p.getLogin().equals(_login))
			{
				return p;
			}
		}
		return null;
	}
	
	private static void addPersonneXML(Personne _p) throws RemoteException
	{
		Element ePersonne = personneToElement(_p);
		racine.addContent(ePersonne);
		enregistre();
	}
	
	private static void supprPersonneXML(Personne _p) throws RemoteException
	{
		Element ePersonne = personneToElement(_p);
		racine.removeContent(ePersonne);
		enregistre();
	}
	
	private static Element personneToElement(Personne _p) throws RemoteException
	{
		Element eNom = new Element("Nom");
		eNom.setText(_p.getNom());
		
		Element ePrenom = new Element("Prenom");
		ePrenom.setText(_p.getPrenom());
		
		Element eLogin = new Element("Login");
		eLogin.setText(_p.getLogin());
		
		Element eMdp = new Element("Mdp");
		eMdp.setText(_p.getMdp());
		
		Element ePersonne = new Element("Personne");
		ePersonne.addContent(eNom);
		ePersonne.addContent(ePrenom);
		ePersonne.addContent(eLogin);
		ePersonne.addContent(eMdp);
		
		return ePersonne;
	}
	
	private static Personne elementToPersonne(Element _e) throws RemoteException
	{
		Personne p = null;
		if(_e.getName().equals("Personne"))
		{
			p = new Personne(_e.getChildText("Nom"),_e.getChildText("Prenom"),_e.getChildText("Login"),_e.getChildText("Mdp"));	
		}
		
		return p;
	}
	
	static void enregistre()
	{
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
	      //avec en argument le nom du fichier pour effectuer la sérialisation.
	      sortie.output(document, new FileOutputStream("Personnes.xml"));
	   }
	   catch (java.io.IOException e){}
	}
}
