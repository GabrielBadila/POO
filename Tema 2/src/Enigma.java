/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         In aceasta clasa au loc toate setarile masinii Enigma, realizandu-se criptarea textului.
 *         </p>
 */
public class Enigma {

	String alfab = null;
	String plug = null;
	char reflector = 0;
	char[] config = null;
	char[] ring = null;
	char[] rot = null;
	char mode = 0;
	String text = null;

	/**
	 * <p>
	 * Constructorul clasei
	 * </p>
	 * 
	 * @param alfab
	 *            - Alfabetul
	 * @param plug
	 *            - Plugboard-ul
	 * @param reflector
	 *            - Reflectorul
	 * @param config
	 *            - Tipul rotoarelor
	 * @param ring
	 *            - Pozitia initiala a inelelor
	 * @param rot
	 *            - Pozitia initiala a rotoarelor
	 * @param text
	 *            - Textul ce se doreste a fi criptat
	 */
	public Enigma(String alfab, String plug, char reflector, char[] config, char[] ring, char[] rot,
			String text) {
		this.alfab = alfab;
		this.plug = plug;
		this.reflector = reflector;
		this.config = config;
		this.ring = ring;
		this.rot = rot;
		this.text = text;
	}

	/**
	 * <p>
	 * Verifica daca o litera face parte din alfabetul masinii
	 * </p>
	 * 
	 * @param alfab
	 *            - Alfabetul
	 * @param c
	 *            - Litera
	 * @return True sau False
	 */
	public boolean SubSir(String alfab, char c) {

		for (int i = 0; i < alfab.length(); i++) {
			if (c == alfab.charAt(i))
				return true;
		}

		return false;

	}

	/**
	 * <p>
	 * Realizeaza rotatia rotoarelor
	 * </p>
	 * 
	 * @param rotor
	 *            - Vectorul de rotoare
	 */
	public void Rotate(Rotor[] rotor) {

		rotor[rotor.length - 1].Inc_Rot();
		for (int i = rotor.length - 2; i >= 1; i--) {
			if (rotor[i + 1].IsNotch(rot[i + 1], Character.getNumericValue(config[i + 1])))
				rotor[i].Inc_Rot();
			else if (rotor[i].IsAlmostNotch(rot[i], Character.getNumericValue(config[i]))) {
				rotor[i].Inc_Rot();
				rotor[i - 1].Inc_Rot();
			}
		}

	}

	/**
	 * <p>
	 * Metoda care realizeaza traseul masinii Enigma.
	 * </p>
	 */
	public void Settings() {

		Plugboard p = new Plugboard(plug);

		RotorType rt = new RotorType();
		Rotor[] rotor = new Rotor[config.length];

		for (int i = 0; i < config.length; i++) {
			rotor[i] = rt.Set(Character.getNumericValue(config[i]));
		}

		Reflector ref = new Reflector();
		
		// Verifica daca textul este diferit de null
		if (text != null)
			// Am folosit eticheta "LoopName" pentru cazul in care o litera care trece prin procesul 
			// de codificare ajunge intr-un caracter ce nu exista in alfabet
			LoopName: for (int i = 0; i < text.length(); i++) {
				
				// Litera ce se doreste a fi codificata
				char c = text.charAt(i);

				// Apelam metoda de roatatie a rotoarelor
				Rotate(rotor);

				// Intrarea prin plugboard
				if (SubSir(alfab, c)) {
					c = p.Codification(c);
				} else
					continue LoopName;

				// Vector pentru distantele dintre cod[Grundstellung] si cod[Ringstellung]
				int[] dist = new int[rotor.length];

				// Criptarea la trecerea prin fiecare rotor
				for (int j = rotor.length - 1; j >= 0; j--) {

					dist[j] = rotor[j].Offset(rot[j], ring[j]);

					if (SubSir(alfab, c))
						c = rotor[j].Codification(c, 1, dist[j]);
					else
						continue LoopName;
				}

				// Criptarea utilizand reflectorul
				if (SubSir(alfab, c))
					c = ref.Reverse(c, reflector);
				else
					continue LoopName;

				// Criptarea inversa la trecerea prin fiecare rotor
				for (int j = 0; j < rotor.length; j++) {

					if (SubSir(alfab, c))
						c = rotor[j].Codification(c, -1, dist[j]);
					else
						continue LoopName;
				}

				// Iesirea prin plugboard
				if (SubSir(alfab, c))
					c = p.Codification(c);
				else
					continue LoopName;

				// Afisare
				System.out.print(c);

			}
	}

}
