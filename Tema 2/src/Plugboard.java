/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Aceasta clasa realizeaza o prima criptare a literelor.
 *         </p>
 *
 */
public class Plugboard {

	public String alfcode;

	/**
	 * <p>
	 * Constructor
	 * </p>
	 * 
	 * @param code
	 *            - Configuratia plugboard-ului
	 */
	public Plugboard(String code) {
		this.alfcode = code;
	}

	/**
	 * <p>
	 * Primeste o litera si o codifica conform plugboard-ului din fisierul de input.
	 * </p>
	 * 
	 * @param c
	 *            - Litera ce se doreste a fi criptata
	 * @return Litera criptata
	 */
	public char Codification(char c) {
		for (int i = 0; i < alfcode.length(); i++) {
			if (alfcode.charAt(i) == c) {
				if (i % 2 == 0)
					return alfcode.charAt(i + 1);
				else
					return alfcode.charAt(i - 1);
			}
		}
		return c;
	}
}