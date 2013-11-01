package Serveur.NotifyLists;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import Serveur.Classes.Personne;

public class UtilisateurNotifyList extends NotifyList<Personne> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7554953552336724604L;

	public UtilisateurNotifyList()
	{
		SAXBuilder sxb = new SAXBuilder();
		this.xmlUrl = "Personnes.xml";
		try
		{
			this.document = sxb.build(new File(this.xmlUrl));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		this.racine = this.document.getRootElement();
		for (Element ePersonne : this.racine.getChildren("Personne"))
		{
			this.add((Personne) this.getObjectFromElement(ePersonne), false);
		}
	}
	
	@Override
	public void removeFromXML(Object _o) {
		if(_o instanceof Personne)
		{
			Personne p = (Personne) _o;
			this.racine.removeContent(this.getElementFromId(p.getLogin()));
		}
		
	}

	@Override
	public void addToXML(Personne _p) {
		Element ePersonne = this.getElementFromObject(_p);
		if (ePersonne != null)
		{
			this.racine.addContent(ePersonne);
			System.out.println("Personne " + _p.getLogin() + " insérée dans le fichier XML.");
		}
	}

	@Override
	protected Element getElementFromId(String _id)
	{
		List<Element> personnes = this.racine.getChildren("Personne");
		Iterator<Element> iterator = personnes.iterator();
		Element personne = null;
		while(iterator.hasNext())
		{
			personne = iterator.next();
			if (personne.getChild("Login").getText().equals(_id))
			{	
				return personne;
			}
		}
		
		return null;
	}

	@Override
	protected Element getElementFromObject(Object _o) {
		Element ePersonne = null;
		if (_o instanceof Personne)
		{
			Personne p = (Personne) _o;
			
			Element eNom = new Element("Nom");
			eNom.setText(p.getNom());
			
			Element ePrenom = new Element("Prenom");
			ePrenom.setText(p.getPrenom());
			
			Element eLogin = new Element("Login");
			eLogin.setText(p.getLogin());
			
			Element eMdp = new Element("Mdp");
			eMdp.setText(p.getMdp());
		
			ePersonne = new Element("Personne");
			ePersonne.addContent(eNom);
			ePersonne.addContent(ePrenom);
			ePersonne.addContent(eLogin);
			ePersonne.addContent(eMdp);
		}
		
		return ePersonne;
	}

	@Override
	protected Object getObjectFromElement(Element _e) {
		Personne p = new Personne(_e.getChildText("Nom"), _e.getChildText("Prenom"), _e.getChildText("Login"), _e.getChildText("Mdp"));
		return p;
	}
}
