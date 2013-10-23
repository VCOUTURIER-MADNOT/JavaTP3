package Serveur.Gestion;

import java.util.ArrayList;

import org.jdom2.Document;

public abstract class NotifyList<E> extends ArrayList<E> {
	
	private static final long serialVersionUID = 1L;

	private Document document;
	
	public int getSize()
	{
		return super.size();
	}
	
	public boolean remove(Object o)
	{
		this.removeFromXML();
		return super.remove(o);
	}
	
	public boolean add(E o)
	{
		this.addToXML();
		return super.add(o);
	}
	
	abstract void addToXML();
	abstract void removeFromXML();
	
}
