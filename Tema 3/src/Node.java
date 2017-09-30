import java.util.ArrayList;

/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         Aceasta clasa reprezinta nodul principal, ce va fi mostenit de toate celelalte tipuri
 *         de noduri.
 *         </p>
 *         
 */

public class Node implements Visitable{

	private String name;
	private ArrayList<Node> children = new ArrayList<Node>();
	private Node parent;
	private int count = 0;
	private int oper = 0;
	
	// Getteri si setteri pentru: nume, copii, parinte, adancime(identare), oper
	// (numarul de copii pe care il pot avea nodurile operator
	
	public Node (String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int n) {
		this.count = n;
	}
	
	public int getOper() {
		return oper;
	}

	public void setOper(int oper) {
		this.oper = oper;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(Node child) {
		this.children.add(child);
		child.setParent(this);
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	// vizitarea nodului si a copiilor lui
	public void accept(Visitor v){
		v.visit(this);
		for(Node node : children){
			node.accept(v);
		}		
	}
	
}
