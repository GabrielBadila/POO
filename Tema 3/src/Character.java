
/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Character
 *         Ajuta la retinerea constantelor fiecarui personaj
 *         </p>
 *         
 */

public class Character {

	private String nume;
	private int value;

	/**
	 * <p>
	 * Constructor
	 * </p>
	 * 
	 * @param name
	 * 			- nume
	 */
	public Character(String nume) {
		this.nume = nume;
	}

	// Getteri si setteri
	
	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
