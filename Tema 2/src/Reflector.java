/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Aceasta clasa realizeaza criptarea literei primite dupa trecerea prin rotoare si
 *         trimiterea ei spre criptarea inversa.
 *         </p>
 *
 */
public class Reflector {

	public static final String B = "AYBRCUDHEQFSGLIPJXKNMOTZVW";
	public static final String C = "AFBVCPDJEIGOHYKRLZMXNWTQSU";

	/**
	 * <p>
	 * Realizeaza criptarea unor litera conform celor doua tipuri: B, C.
	 * </p>
	 * 
	 * @param c
	 *            - Litera ce se doreste a fi criptata
	 * @param type
	 *            - Tipul criptarii
	 * @return Litera criptata
	 */
	public char Reverse(char c, char type) {

		if (type == 'B')
			for (int i = 0; i < B.length(); i++) {
				if (B.charAt(i) == c) {
					if (i % 2 == 0)
						return B.charAt(i + 1);
					else
						return B.charAt(i - 1);
				}
			}
		else if (type == 'C')
			for (int i = 0; i < C.length(); i++) {
				if (C.charAt(i) == c) {
					if (i % 2 == 0)
						return C.charAt(i + 1);
					else
						return C.charAt(i - 1);
				}
			}

		return c;
	}
}