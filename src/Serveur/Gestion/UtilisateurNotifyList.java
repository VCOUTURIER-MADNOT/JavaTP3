package Serveur.Gestion;

import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;

import Serveur.Classes.Personne;

public class UtilisateurNotifyList extends NotifyList<Personne> {

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
		
		this.racine.addContent(ePersonne);
	}

	protected Element getElementFromId(String _id)
	{
		List<Element> logins = this.racine.getChildren("Login");
		Iterator<Element> iterator = logins.iterator();
		Element login = null;
		while(iterator.hasNext() && !( (login = iterator.next()).getText().equals(_id)));
		if(login != null)	
			return login.getParentElement();
		
		return null;
	}
}
