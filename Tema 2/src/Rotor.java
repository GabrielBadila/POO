/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Aceasta clasa realizeaza toate operatiile ce tin de rotoare.
 *         </p>
 */
public class Rotor {

	public static final String alfab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String code;
	public char notch;
	public char notch2;
	public int rotations = 0;

	/**
	 * <p>
	 * Constructorul rotoarelor cu un singur notch.
	 * </p>
	 * 
	 * @param code
	 *            - Codificarea rotorului
	 * @param notch
	 *            - Notch-ul rotorului
	 */
	public Rotor(String code, char notch) {
		this.code = code;
		this.notch = notch;
	}

	/**
	 * <p>
	 * Constructorul rotoarelor cu doua notch-uri.
	 * </p>
	 * 
	 * @param code
	 *            - Codificarea rotorului
	 * @param notch
	 *            - Primul notch al rotorului
	 * @param notch2
	 *            - Al doilea notch al rotorului
	 */
	public Rotor(String code, char notch, char notch2) {
		this.code = code;
		this.notch = notch;
		this.notch2 = notch2;
	}

	/**
	 * <p>
	 * Realizeaza codificarea literei in momentul trecerii prin rotor.
	 * </p>
	 * 
	 * @param c
	 *            - Litera ce se doreste a fi codificata
	 * @param direction
	 *            - Directia de codificare (dus/intors)
	 * @param dist
	 *            - Distanta dintre cod[Grundstellung] si cod[Ringstellung]
	 * @return Codificarea literei la iesirea din rotor
	 */
	public char Codification(char c, int direction, int dist) {
		int index = alfab.indexOf(c);
		index = (index + dist) % 26;

		// Mapare dus
		if (direction == 1) {
			char letter = code.charAt(index);
			index = alfab.indexOf(letter);
			// Mapare intors
		} else {
			char letter = alfab.charAt(index);
			index = code.indexOf(letter);
		}

		index = (index - dist + 26) % 26;
		return alfab.charAt(index);
	}

	/**
	 * <p>
	 * Aflarea distantei dintre cod[Grundstellung] si cod[Ringstellung].
	 * </p>
	 * 
	 * @param rot
	 *            - Pozitia initiala a rotorului
	 * @param ring
	 *            - Pozitia initiala a inelului
	 * @return Distanta
	 */
	public int Offset(char rot, char ring) {
		int x = alfab.indexOf(rot);
		int y = alfab.indexOf(ring);
		int dist = (x - y + rotations + 26) % 26;
		return dist;
	}

	/**
	 * <p>
	 * Incrementeaza numarul rotatiilor.
	 * </p>
	 * 
	 * @return Returneaza numarul rotatiilor
	 */
	public int Inc_Rot() {
		return (rotations++) % 26;
	}

	/**
	 * <p>
	 * Verifica daca rotorul se afla la notch.
	 * </p>
	 * 
	 * @param c
	 *            - Pozitia initiala a rotorului
	 * @param type
	 *            - Tipul rotorului
	 * @return True sau False
	 */
	public boolean IsNotch(char c, int type) {
		if (type <= 5)
			return ((alfab.indexOf(c) + rotations) % 26 == alfab.indexOf(notch));
		else
			return ((alfab.indexOf(c) + rotations) % 26 == alfab.indexOf(notch)
					|| (alfab.indexOf(c) + rotations) % 26 == alfab.indexOf(notch2));
	}

	/**
	 * <Verifica daca rotorul se afla cu o pozitie inainte de notch.
	 * </p>
	 * 
	 * @param c
	 *            - Pozitia initiala a rotorului
	 * @param type
	 *            - Tipul rotorului
	 * @return True sau False
	 */
	public boolean IsAlmostNotch(char c, int type) {
		if (type <= 5)
			return ((alfab.indexOf(c) + rotations) % 26 == alfab.indexOf(notch) - 1);
		else
			return ((alfab.indexOf(c) + rotations) % 26 == alfab.indexOf(notch) - 1
					|| (alfab.indexOf(c) + rotations) % 26 == alfab.indexOf(notch2) - 1);
	}

}
