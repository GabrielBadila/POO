
/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Interfata Visitor
 *         </p>
 *         
 */

public interface Visitor {
	public void visit(Node node);
	public void visit(ActNode act);	
	public void visit(SceneNode scene);
	public void visit(AssignmentNode assignment);
	public void visit(LvalNode lval);
	public void visit(RvalNode rval);
	public void visit(ConstantNode constant);
	public void visit(OperatorNode operator);
	public void visit(OutputNode output);
}