import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Aceasta clasa realizeaza citirea din fisierele de input si instantierea unui obiect de
 *         tip Enigma (clasa in care este implementata intreaga tema).
 *         </p>
 */
public class Main {

	/**
	 * <p>
	 * Realizeaza citirea din fisier si apelarea obiectului de tip Enigma.
	 * </p>
	 */
	public static void main(String[] args) {
		int counter = 0;
		String alfab = null;
		String plug = null;
		char reflector = 0;
		char[] config = null;
		char[] ring = null;
		char[] rot = null;
		// char mode = 0; 	// variabila in care se salveaza tipul operatiei (criptare/decriptare)
							// am decis sa-l comentez pentru ca cele doua operatii sunt similare
		String text = null;

		// Citirea din fisiere
		Path file = Paths.get(args[0]);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {

				counter++;
				line = line.replace("(", "");
				line = line.replace(")", "");
				line = line.replace(" ", "");
				line = line.replace("	", "");

				switch (counter) {
				case 1:
					alfab = line; // alfabetul de la input
					break;
				case 2:
					plug = line; // codificarea plugboard-ului
					break;
				case 3:
					reflector = line.charAt(0); // tipul reflectorului
					config = new char[line.length() - 1];
					for (int i = 0; i < line.length() - 1; i++)
						config[i] = line.charAt(i + 1); // tipul rotoarelor
					break;
				case 4:
					ring = line.toCharArray(); // pozitia initiala a inelelor
					break;
				case 5:
					rot = line.toCharArray(); // pozitia initiala a rotoarelor
					break;
				case 6:
					// mode = line.charAt(0); // tipul operatiei
					break;
				default:
					if (text == null)
						text = line;
					else
						text = text + line; // textul de criptat
					break;
				}

			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		Enigma enigma = new Enigma(alfab, plug, reflector, config, ring, rot, text);
		enigma.Settings();
	}

}
