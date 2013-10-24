package Serveur.Gestion;

import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;

import Serveur.Classes.Forum;
import Serveur.Classes.Personne;

public class ForumNotifyList extends NotifyList<Forum>{

	@Override
	public void addToXML(Forum _o) {
		Element eForum = new Element("Forum");
		
		Element eSujet = new Element("Sujet");
		eSujet.setText(_o.getSujet());
		
		eForum.addContent(eSujet);
		
		this.racine.addContent(eForum);
	}

	@Override
	public void removeFromXML(Object _o) {
		if(_o instanceof Forum)
		{
			Forum f = (Forum) _o;
			this.racine.removeContent(this.getElementFromId(f.getSujet()));
		}
	}
	
	public Element getElementFromId(String _id)
	{
		List<Element> sujets = this.racine.getChildren("Sujet");
		Iterator<Element> iterator = sujets.iterator();
		Element sujet = null;
		while(iterator.hasNext() && !( (sujet = iterator.next()).getText().equals(_id)));
		if(sujet != null)	
			return sujet.getParentElement();
		
		return null;
	}
}
