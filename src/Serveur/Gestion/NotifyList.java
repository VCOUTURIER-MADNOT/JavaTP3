package Serveur.Gestion;

import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;

public abstract class NotifyList<E> extends ArrayList<E> {
	
	protected Document document;
	protected Element racine;
	
	public abstract void addToXML(E _o);
	public abstract void removeFromXML(Object _o);
	protected abstract Element getElementFromId(String _id);
	
	public int getSize()
	{
		return super.size();
	}
	
	public boolean remove(Object _o)
	{
		this.removeFromXML(_o);
		return super.remove(_o);
	}
	
	public boolean add(E _o)
	{
		if(this.contains(_o))
		{
			this.addToXML(_o);
			return super.add(_o);
		}
		return false;
	}
}