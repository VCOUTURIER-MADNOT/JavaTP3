package Serveur;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Serveur.Gestion.GestionForum;
import Serveur.Gestion.GestionUtilisateur;

 
/**
 * Serveur qui cree une personne et la met a disposition des clients.
 * @author Cyril Rabat
 * @version 08/10/2013
 */
public class ServeurRMI {
 
	public static Registry registry;
    /**
     * Methode principale.
     * @param args inutilise
     */
    public static void main(String[] args) {
		try {
			registry = LocateRegistry.createRegistry(12345);
			GestionUtilisateur GU = new GestionUtilisateur();
			GestionForum GF = new GestionForum();
			registry.bind("Serveur/GestionUtilisateur", GU);
			registry.bind("Serveur/GestionForum", GF);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			
		}
		System.out.println("Serveur démarré !");
    }
 
}