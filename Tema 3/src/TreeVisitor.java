import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Aceasta clasa reprezinta TreeVisitor-ul, aici se realizeaza scrierea arborilor in fisiere.
 *         </p>
 *         
 */

public class TreeVisitor implements Visitor {

	private FileWriter write;
	private PrintWriter p;

	/**
	 * <p>
	 * Constructor
	 * </p>
	 */
	public TreeVisitor(){
		write = null;
		p = null;
	}
	
	// Getter
	public PrintWriter getP(){
		return p;
	}
	
	/**
	 * <p>
	 * Metoda ce realizeaza deschiderea fisierelor
	 * </p>
	 * 
	 * @param file
	 * 			- numele fisierului in care se va scrie
	 */
	public void OpenFile(String file) {
		try {
			write = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p = new PrintWriter(write);
	}
	
	/**
	 * <p>
	 * Metoda ce realizeaza inchiderea fisierelor
	 * </p>
	 */
	public void CloseFile(){
		p.close();
	}
	
	/**
	 * <p>
	 * Afisarea arborelui incepand cu nodul de baza (ProgramNode)
	 * </p>
	 */
	@Override
	public void visit(Node node){
		for(int i=0; i<node.getCount(); i++)
			p.print("\t");
		p.println(node.getName());
	}

	/**
	 * <p>
	 * Afisarea arborelui incepand cu ActNode-urile
	 * </p>
	 */
	@Override
	public void visit(ActNode act) {
		for(int i=0; i<act.getCount(); i++)
			p.print("\t");
		p.println(act.getName());
	}

	/**
	 * <p>
	 * Afisarea arborelui incepand cu SceneNode-urile
	 * </p>
	 */
	@Override
	public void visit(SceneNode scene) {
		for(int i=0; i<scene.getCount(); i++)
			p.print("\t");
		p.println(scene.getName());
	}

	/**
	 * <p>
	 * Afisarea arborelui incepand cu AssignmentNode-urile
	 * </p>
	 */
	@Override
	public void visit(AssignmentNode assignment) {
		for(int i=0; i<assignment.getCount(); i++)
			p.print("\t");
		p.println(assignment.getName());
	}

	/**
	 * <p>
	 * Afisarea arborelui incepand cu LvalNode-urile
	 * </p>
	 */
	@Override
	public void visit(LvalNode lval) {
		for(int i=0; i<lval.getCount(); i++)
			p.print("\t");
		p.println(lval.getName());
	}

	/**
	 * <p>
	 * Afisarea arborelui incepand cu RvalNode-urile
	 * </p>
	 */
	@Override
	public void visit(RvalNode rval) {
		for(int i=0; i<rval.getCount(); i++)
			p.print("\t");
		p.println(rval.getName());
	}

	/**
	 * <p>
	 * Afisarea arborelui incepand cu ConstantNode-urile
	 * </p>
	 */
	@Override
	public void visit(ConstantNode constant) {
		for(int i=0; i<constant.getCount(); i++)
			p.print("\t");
		p.println(constant.getName());
	}
	
	/**
	 * <p>
	 * Afisarea arborelui incepand cu OperatorNode-urile
	 * </p>
	 */
	@Override
	public void visit(OperatorNode operator) {
		for(int i=0; i<operator.getCount(); i++)
			p.print("\t");
		p.println(operator.getName());
	}

	/**
	 * <p>
	 * Afisarea arborelui incepand cu OutputNode-urile
	 * </p>
	 */
	@Override
	public void visit(OutputNode output) {
		for(int i=0; i<output.getCount(); i++)
			p.print("\t");
		p.println(output.getName());
	}

}
