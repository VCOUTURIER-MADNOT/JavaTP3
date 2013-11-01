package Client;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Serveur.Interfaces.IGestionForum;
import Serveur.Interfaces.IGestionUtilisateur;
 
/**
 * Client permettant d'interroger la personne sur le serveur distant.
 * @author Cyril Rabat
 * @version 08/10/2013
 */
public class ClientRMI {
 
    /**
     * Methode principale.
     * @param args inutilise
     */
    public static void main(String[] args){
    	IGestionUtilisateur GU = null;
    	IGestionForum GF = null;
    	try {
    		Registry registry = LocateRegistry.getRegistry(12345);
			GU = (IGestionUtilisateur) registry.lookup("Serveur/GestionUtilisateur");
			GU.addPersonneList("leal", "lel", "leael", "lel");
			
			GF = (IGestionForum) registry.lookup("Serveur/GestionForum");
			GF.creerForum("bonjour");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
 
}