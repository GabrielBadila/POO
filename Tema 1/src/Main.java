import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * This execution entry point class handles parsing and executing commands from
 * the input file.
 * </p>
 */
public class Main {

	/**
	 * <p>
	 * Execution entry point.
	 * </p>
	 *
	 * @param args the name of the file containing commands to be executed
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java -cp <classpath> Main <input file>");
			System.exit(1);
		}

		Network x = Network.getInstance(); // instantiere unica
		FileParser f = new FileParser(args[0]);

		// deschidere fisier
		f.open();

		List<String> lista = new ArrayList<String>();

		for (;;) {
			
			// parcurgerea linie cu linie a fisierelor de intrare
			lista = f.parseNextLine();
			if (lista == null)
				break;

			// apelare ADD
			if (Objects.equals(lista.get(0), "add")) {
				int ID = Integer.parseInt(lista.get(1));
				x.ADD(ID, lista.get(2));
			}

			// apelare REMOVE
			if (Objects.equals(lista.get(0), "remove")) {
				int ID = Integer.parseInt(lista.get(1));
				x.REMOVE(ID);
			}

			// apelare FRIEND
			if (Objects.equals(lista.get(0), "friend")) {
				int ID1 = Integer.parseInt(lista.get(1));
				int ID2 = Integer.parseInt(lista.get(2));
				x.FRIEND(ID1, ID2);
			}

			// apelare UNFRIEND
			if (Objects.equals(lista.get(0), "unfriend")) {
				int ID1 = Integer.parseInt(lista.get(1));
				int ID2 = Integer.parseInt(lista.get(2));
				x.UNFRIEND(ID1, ID2);
			}

			if (Objects.equals(lista.get(0), "print")) {
				
				// apelare PRINT_NETWORK
				if (Objects.equals(lista.get(1), "network"))
					x.PRINT_NETWORK();
				
				// apelare PRINT_USER
				if (Objects.equals(lista.get(1), "user")) {
					int ID = Integer.parseInt(lista.get(2));
					x.PRINT_USER(ID);
				}
				
				// apelare PRINT_COMMUNITIES
				if (Objects.equals(lista.get(1), "communities"))
					x.PRINT_COMMUNITIES();
				
				// apelare PRINT_STRENGTH
				if (Objects.equals(lista.get(1), "strength")) {
					int ID = Integer.parseInt(lista.get(2));
					x.PRINT_STRENGTH(ID);
				}
			}

		}

		// inchidere fisier
		f.close();

	}

}
