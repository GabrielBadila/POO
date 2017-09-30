import java.util.ArrayList;

/**
 * 
 * @author Gabriel Badila
 * 
 * <p>
 * Aceasta este clasa principala.
 * </p>
 * 
 * <p>
 * Contine informatiile principale ale unui utilizator: id si nume.
 * Are doi constructori.
 * </p>
 */
public class User {
	protected int ID; // retine id-ul unui utilizator
	protected String Nume; // retine numele unui utilizator
	protected int vizitat = -1; // variabila utilizata de metodele DFS si BFS
	protected int distanta = 0; // variabila utilizata de metoda DFS
	protected ArrayList<User> Prieteni = new ArrayList<User>();

	public User() {

	}

	public User(int ID, String Nume) {
		this.ID = ID;
		this.Nume = Nume;
	}

}
