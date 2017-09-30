/**
 * 
 * @author Gabriel Badila
 *
 *         <p>
 *         Aceasta clasa realizeaza alegerea si setarea rotoarelor.
 *         </p>
 */
public class RotorType {

	public static final String rot1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
	public static final String rot2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
	public static final String rot3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
	public static final String rot4 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
	public static final String rot5 = "VZBRGITYUPSDNHLXAWMJQOFECK";
	public static final String rot6 = "JPGVOUMFYQBENHZRDKASXLICTW";
	public static final String rot7 = "NZJHGRCXMYSWBOUFAIVLPEKQDT";
	public static final String rot8 = "FKQHTLXOCBJSPDZRAMEWNIUYGV";

	public static final char not1 = 'R';
	public static final char not2 = 'F';
	public static final char not3 = 'W';
	public static final char not4 = 'K';
	public static final char not5 = 'A';
	public static final char notA = 'A';
	public static final char notN = 'N';

	/**
	 * <p>
	 * Realizeaza alegerea si setarea rotorului.
	 * </p>
	 * 
	 * @param type
	 *            - Tipul rotorului din fisierul de input (1, 2, ..., 8)
	 * @return Instantierea rotorului
	 */
	public Rotor Set(int type) {
		switch (type) {
		case 1:
			return new Rotor(rot1, not1);
		case 2:
			return new Rotor(rot2, not2);
		case 3:
			return new Rotor(rot3, not3);
		case 4:
			return new Rotor(rot4, not4);
		case 5:
			return new Rotor(rot5, not5);
		case 6:
			return new Rotor(rot6, notA, notN);
		case 7:
			return new Rotor(rot7, notA, notN);
		default:
			return new Rotor(rot8, notA, notN);
		}
	}
}
