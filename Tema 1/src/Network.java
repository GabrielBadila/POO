import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Gabriel Badila
 * 
 * <p>
 * In aceasta clasa sunt implementate toate metodele ce tin de utilizatori.
 * </p>
 * 
 * <p>
 * Aceasta clasa extinde clasa <i>User</i>.
 * </p>
 */
public class Network extends User {

	/**
	 * Structura de date ce contine elemente de tip <i>User</i> ordonate 
	 * crescator dupa ID.
	 */
	private ArrayList<User> Utilizatori = new ArrayList<User>();
	
	/**
	 * Unica instantiere a clasei Network
	 */
	private static final Network INSTANCE = new Network();

	/**
	 * <p>
	 * Constructor care suprascrie constructorul default.
	 * </p>
	 */
	private Network() {}

	/**
	 * <p>
	 * Metoda de returnare a instantei
	 * </p>
	 */
	public static Network getInstance() {
		return INSTANCE;
	}

	/**
	 * <p>
	 * Adauga un utilizator.
	 * </p>
	 * 
	 * <p>
	 * Aceasta metoda parcurge toti utilizatorii, iar daca gaseste utilizatorul
	 * ce se vrea a fi adaugat afiseaza "DUPLICATE", altfel cauta pozitia pe 
	 * care trebuie adaugat (lista trebuie sa ramana ordonata) si il adauga 
	 * afisand mesajul "OK".
	 * </p>
	 * 
	 * @param ID Id-ul utilizatorului ce urmeaza a fi adaugat.
	 * @param Nume Numele utilizatorului ce urmeaza a fi adaugat.
	 */
	public void ADD(int ID, String Nume) {

		int check = 0;
		User x = new User(ID, Nume);
		for (int i = 0; i < Utilizatori.size(); i++) {
			if (ID == Utilizatori.get(i).ID) {
				check = 1;
				break;
			}
		}

		// Caut pozitia pe care trebuie adaugat utilizatorul
		if (check == 0) {
			if (Utilizatori.size() == 0) {
				Utilizatori.add(x);
			} else if (Utilizatori.size() == 1) {
				if (ID > Utilizatori.get(0).ID) {
					Utilizatori.add(x);
				} else if (ID < Utilizatori.get(0).ID) {
					Utilizatori.add(0, x);
				}
			} else {
				for (int i = 0; i < Utilizatori.size() - 1; i++) {
					if (ID < Utilizatori.get(0).ID) {
						Utilizatori.add(0, x);
						break;
					}
					if (ID > Utilizatori.get(Utilizatori.size() - 1).ID) {
						Utilizatori.add(x);
						break;
					}
					if (ID > Utilizatori.get(i).ID
							&& ID < Utilizatori.get(i + 1).ID) {
						Utilizatori.add(i + 1, x);
						break;
					}
				}
			}
			System.out.println("OK");
		} else
			System.out.println("DUPLICATE");

	}

	/**
	 * <p>
	 * Elimina un utilizator pe baza unui ID primit.
	 * </p>
	 * 
	 * <p>
	 * Cauta utilizatorul cu id-ul ID, iar daca il gaseste ii sterge relatiile 
	 * de prietenie si il elimina, afisand mesajul "OK", altfel afiseaza 
	 * "INEXISTENT".
	 * </p>
	 * 
	 * @param ID Id-ul utilizatorului ce se doreste a fi sters.
	 */
	public void REMOVE(int ID) {

		int check = 0;
		for (int i = 0; i < Utilizatori.size(); i++) {
			if (ID == Utilizatori.get(i).ID) {
				int size = Utilizatori.get(i).Prieteni.size();
				for (int j = 0; j < size; j++) {
					UNFRIEND_REMOVE(ID, Utilizatori.get(i).Prieteni.get(0).ID);
				}
				Utilizatori.remove(i);
				check = 1;
				System.out.println("OK");
				break;
			}
		}

		if (check == 0)
			System.out.println("INEXISTENT");

	}

	/**
	 * <p>
	 * Va realiza o relatia de prietenie intre doi utilizatori.
	 * </p>
	 * 
	 * <p>
	 * Verifica daca cele doua id-uri exista, iar daca exista, se realizeaza 
	 * o parcurgere a utilizatorului cu id-ul ID1 si se realizeaza relatia cu 
	 * utilizatorul cu id-ul ID2 si reciproca. Daca operatia s-a realizat cu 
	 * succes se afiseaza "OK", altfel "INEXISTENT".
	 * </p>
	 * 
	 * @param ID1 Id-ul primului utilizator.
	 * @param ID2 Id-ul celui de-al doilea utilizator.
	 */
	public void FRIEND(int ID1, int ID2) {

		int pos1 = 0;
		int pos2 = 0;
		int ok = 0;

		// Verific ca cele doua id-uri sa nu fie egale
		if (ID1 == ID2)
			System.out.println("INEXISTENT");
		else {
			for (int i = 0; i < Utilizatori.size(); i++) {
				if (ID1 == Utilizatori.get(i).ID) {
					pos1 = i;
					ok++;
				}
				if (ID2 == Utilizatori.get(i).ID) {
					pos2 = i;
					ok++;
				}
			}

			// Caut pozitia pe care trebuie adaugat fiecare prieten
			if (ok == 2) {
				if (Utilizatori.get(pos1).Prieteni.size() == 0) {
					Utilizatori.get(pos1).Prieteni.add(Utilizatori.get(pos2));
				} else if (Utilizatori.get(pos1).Prieteni.size() == 1) {
					if (ID2 > Utilizatori.get(pos1).Prieteni.get(0).ID) {
						Utilizatori.get(pos1).Prieteni
								.add(Utilizatori.get(pos2));
					} else if (ID2 < Utilizatori.get(pos1).Prieteni.get(0).ID) {
						Utilizatori.get(pos1).Prieteni.add(0,
								Utilizatori.get(pos2));
					}
				} else {
					for (int i = 0; i < Utilizatori.get(pos1).Prieteni.size()
							- 1; i++) {
						if (ID2 < Utilizatori.get(pos1).Prieteni.get(0).ID) {
							Utilizatori.get(pos1).Prieteni.add(0,
									Utilizatori.get(pos2));
							break;
						}

						if (ID2 > Utilizatori.get(pos1).Prieteni.get(
								Utilizatori.get(pos1).Prieteni.size() - 1).ID) {
							Utilizatori.get(pos1).Prieteni
									.add(Utilizatori.get(pos2));
							break;
						}

						if (ID2 > Utilizatori.get(pos1).Prieteni.get(i).ID
								&& ID2 < Utilizatori.get(pos1).Prieteni
										.get(i + 1).ID) {
							Utilizatori.get(pos1).Prieteni.add(i + 1,
									Utilizatori.get(pos2));
							break;
						}
					}
				}

				if (Utilizatori.get(pos2).Prieteni.size() == 0) {
					Utilizatori.get(pos2).Prieteni.add(Utilizatori.get(pos1));
				} else if (Utilizatori.get(pos2).Prieteni.size() == 1) {
					if (ID1 > Utilizatori.get(pos2).Prieteni.get(0).ID) {
						Utilizatori.get(pos2).Prieteni
								.add(Utilizatori.get(pos1));
					} else if (ID1 < Utilizatori.get(pos2).Prieteni.get(0).ID) {
						Utilizatori.get(pos2).Prieteni.add(0,
								Utilizatori.get(pos1));
					}
				} else {
					for (int i = 0; i < Utilizatori.get(pos2).Prieteni.size()
							- 1; i++) {
						if (ID1 < Utilizatori.get(pos2).Prieteni.get(0).ID) {
							Utilizatori.get(pos2).Prieteni.add(0,
									Utilizatori.get(pos1));
							break;
						}

						if (ID1 > Utilizatori.get(pos2).Prieteni.get(
								Utilizatori.get(pos2).Prieteni.size() - 1).ID) {
							Utilizatori.get(pos2).Prieteni
									.add(Utilizatori.get(pos1));
							break;
						}

						if (ID1 > Utilizatori.get(pos2).Prieteni.get(i).ID
								&& ID1 < Utilizatori.get(pos2).Prieteni
										.get(i + 1).ID) {
							Utilizatori.get(pos2).Prieteni.add(i + 1,
									Utilizatori.get(pos1));
							break;
						}
					}
				}

				System.out.println("OK");
			} else
				System.out.println("INEXISTENT");

		}

	}

	/**
	 * <p>
	 * Elimina relatia de prietenie dintre doi utilizatori.
	 * </p>
	 * 
	 * <p>
	 * Daca cele doua id-uri exista se realizeaza o cautare printre prietenii 
	 * utilizatorului cu id-ul ID1, iar cand prietenul cu id-ul ID2 este gasit 
	 * se elimina relatia de prietenie si viceversa, apoi se afiseaza mesajul 
	 * "OK". Daca cel putin un id nu exista se afiseaza mesajul "INEXISTENT".
	 * </p>
	 * 
	 * @param ID1 Id-ul primului utilizator.
	 * @param ID2 Id-ul celui de-al doilea utilizator.
	 */
	public void UNFRIEND(int ID1, int ID2) {

		int u1 = 0;
		int u2 = 0;
		int p1 = 0;
		int p2 = 0;
		int ok = 0;

		// Verific ca cele doua id-uri sa nu fie egale
		if (ID1 == ID2)
			System.out.println("INEXISTENT");
		else {
			for (int i = 0; i < Utilizatori.size(); i++) {
				if (ID1 == Utilizatori.get(i).ID) {
					for (int j = 0; j < Utilizatori.get(i).Prieteni
							.size(); j++) {
						if (ID2 == Utilizatori.get(i).Prieteni.get(j).ID) {
							u1 = i;
							p2 = j;
							ok++;
							break;
						}
					}
				}
				if (ID2 == Utilizatori.get(i).ID) {
					for (int j = 0; j < Utilizatori.get(i).Prieteni
							.size(); j++) {
						if (ID1 == Utilizatori.get(i).Prieteni.get(j).ID) {
							u2 = i;
							p1 = j;
							ok++;
						}
					}
				}
			}

			if (ok == 2) {
				Utilizatori.get(u1).Prieteni.remove(p2);
				Utilizatori.get(u2).Prieteni.remove(p1);

				System.out.println("OK");
			} else
				System.out.println("INEXISTENT");
		}

	}

	/**
	 * <p>
	 * Elimina relatia de prietenie dintre doi utilizatori.
	 * </p>
	 * 
	 * <p>
	 * Este identica cu metoda <i>UNFRIEND</i> de mai sus, singura deosebire 
	 * fiind lipsa mesajelor. Aceasta metoda este folosita doar in cadrul 
	 * metodei <i>REMOVE</i>.
	 * </p>
	 * 
	 * @param ID1 Id-ul primului utilizator.
	 * @param ID2 Id-ul celui de-al doilea utilizator.
	 */
	public void UNFRIEND_REMOVE(int ID1, int ID2) {

		int u1 = 0;
		int u2 = 0;
		int p1 = 0;
		int p2 = 0;
		int ok = 0;

		// Verific ca cele doua id-uri sa nu fie egale
		if (ID1 == ID2)
			return;
		else {
			for (int i = 0; i < Utilizatori.size(); i++) {
				if (ID1 == Utilizatori.get(i).ID) {
					for (int j = 0; j < Utilizatori.get(i).Prieteni
							.size(); j++) {
						if (ID2 == Utilizatori.get(i).Prieteni.get(j).ID) {
							u1 = i;
							p2 = j;
							ok++;
							break;
						}
					}
				}
				if (ID2 == Utilizatori.get(i).ID) {
					for (int j = 0; j < Utilizatori.get(i).Prieteni
							.size(); j++) {
						if (ID1 == Utilizatori.get(i).Prieteni.get(j).ID) {
							u2 = i;
							p1 = j;
							ok++;
						}
					}
				}
			}

			if (ok == 2) {
				Utilizatori.get(u1).Prieteni.remove(p2);
				Utilizatori.get(u2).Prieteni.remove(p1);
			} else
				return;
		}

	}

	/**
	 * <p>
	 * Aplica metoda DFS asupra unui utilizator si returneaza o lista cu 
	 * id-urile utilizatorilor intalniti dupa aplicare.
	 * </p>
	 * 
	 * @param a Utilizatorul pe care se aplica DFS.
	 * @return O lista plina de id-uri.
	 */
	public List<Integer> DFS(User a) {
		
		// Stiva pentru DFS
		Stack<User> s = new Stack<User>();
		
		// Lista ce va fi returnata
		List<Integer> conex = new ArrayList<Integer>();
		
		int check = 0; // checker
		
		a.vizitat = 0;
		s.push(a);
		conex.add(a.ID);

		while (!s.isEmpty()) {
			User b = new User();
			b = s.peek();
			for (int i = 0; i < b.Prieteni.size(); i++) {
				if (b.Prieteni.get(i).vizitat == -1) {
					b.Prieteni.get(i).vizitat = 0;
					conex.add(b.Prieteni.get(i).ID);
					s.push(b.Prieteni.get(i));
					check = 1;
					break;
				}
			}

			if (check == 0) {
				b.vizitat = 1;
				s.pop();
			}

			check = 0;
		}

		return conex;

	}

	/**
	 * <p>
	 * Aplica metoda BFS asupra unui utilizator si returneaza cea mai mare 
	 * distanta gasita (cele mai multe nivele parcurse).
	 * </p>
	 * 
	 * @param a Utilizatorul pe care se aplica BFS.
	 * @return Distanta maxima
	 */
	public int BFS(User a) {

		// Coada (lista) pentru BFS
		LinkedList<User> q = new LinkedList<User>();

		a.vizitat = 0;
		a.distanta = 0;
		q.addLast(a);

		while (q.size() != 0) {
			User b = q.pollFirst();
			for (int i = 0; i < b.Prieteni.size(); i++) {
				if (b.Prieteni.get(i).vizitat == -1) {
					b.Prieteni.get(i).vizitat = 0;
					b.Prieteni.get(i).distanta = b.distanta + 1;
					q.addLast(b.Prieteni.get(i));
				}
			}
			b.vizitat = 1;

		}
		
		int D = 0;

		for (int i = 0; i < Utilizatori.size(); i++) {
			if (Utilizatori.get(i).distanta > D)
				D = Utilizatori.get(i).distanta;
		}

		for (int i = 0; i < Utilizatori.size(); i++) {
			Utilizatori.get(i).vizitat = -1;
			Utilizatori.get(i).distanta = 0;
		}

		return D;

	}

	/**
	 * <p>
	 * Afiseaza id-ul si numele unui utilizator, precum si id-urile prietenilor 
	 * lui. Daca utilizatorul nu exista se afiseaza mesajul "INEXISTENT".
	 * </p>
	 * 
	 * @param ID Id-ul utilizatorului pentru care se doreste afisarea.
	 */
	public void PRINT_USER(int ID) {
		
		int check = 0;
		for (int i = 0; i < Utilizatori.size(); i++) {
			if (ID == Utilizatori.get(i).ID) {
				System.out.print(Utilizatori.get(i).ID + " "
						+ Utilizatori.get(i).Nume + ": ");
				check = 1;
				for (int j = 0; j < Utilizatori.get(i).Prieteni.size(); j++) {
					System.out
							.print(Utilizatori.get(i).Prieteni.get(j).ID + " ");
				}
				System.out.println();
			}
		}

		if (check == 0) {
			System.out.println("INEXISTENT");
		}
		
	}

	/**
	 * <p>
	 * Afiseaza pentru fiecare utilizator: id-ul, numele si prietenii lui.
	 * </p>
	 */
	public void PRINT_NETWORK() {

		for (int i = 0; i < Utilizatori.size(); i++) {
			System.out.print(Utilizatori.get(i).ID + " "
					+ Utilizatori.get(i).Nume + ": ");
			for (int j = 0; j < Utilizatori.get(i).Prieteni.size(); j++) {
				System.out.print(Utilizatori.get(i).Prieteni.get(j).ID + " ");
			}
			System.out.println();
		}

	}

	/**
	 * <p>
	 * Afiseaza toate comunitatile cu ajutorul metodei <i>DFS</i> de mai sus.
	 * </p>
	 */
	public void PRINT_COMMUNITIES() {

		for (int i = 0; i < Utilizatori.size(); i++) {
			if (Utilizatori.get(i).vizitat != 1) {
				List<Integer> v = DFS(Utilizatori.get(i));

				System.out.print(v.size() + ": ");

				for (int j = 0; j < v.size(); j++) {
					System.out.print(v.get(j) + " ");
				}
				System.out.println();
			}
		}

		for (int i = 0; i < Utilizatori.size(); i++) {
			Utilizatori.get(i).vizitat = -1;
		}

	}

	/**
	 * <p>
	 * Afiseaza gradul de socializare al unei comunitati.
	 * </p>
	 * 
	 * <p>
	 * Utilizeaza metoda <i>DFS</i> pentru un utilizator, iar apoi utilizeaza 
	 * metoda <i>BFS</i> pentru fiecare utilizator corespunzator id-urilor 
	 * returnate de <i>DFS</i>. In final apeleaza o formula matematica si 
	 * afiseaza gradul de socializare. Daca utilizator al carui id este primit 
	 * ca parametru nu exista este afisat mesajul "INEXISTENT".
	 * </p>
	 */
	public void PRINT_STRENGTH(int ID) {

		int check = 0; // checker
		int g = 0; // variabila ce retine gradul de socializare
		int max = -1; // diametrul comunitatii
		int dist = -1; // variabila ce retine distanta la un moment dat

		for (int i = 0; i < Utilizatori.size(); i++) {
			if (ID == Utilizatori.get(i).ID) {
				List<Integer> v = DFS(Utilizatori.get(i));
				int N = v.size(); // numarul de utilizatori din comunitate
				check = 1;

				for (int j = 0; j < Utilizatori.size(); j++) {
					Utilizatori.get(j).vizitat = -1;
				}

				if (N != 1) {
					for (int j = 0; j < N; j++) {
						for (int k = 0; k < Utilizatori.size(); k++) {
							if (v.get(j) == Utilizatori.get(k).ID) {
								dist = BFS(Utilizatori.get(k));
							}
						}

						if (max < dist)
							max = dist;
					}
					
					// Utilizez formula de determinare a gradului de socializare
					g = (N - max) * 100 / (N - 1);
				}

			}
		}

		if (check == 0)
			System.out.println("INEXISTENT");
		else
			System.out.println(g);

	}

}