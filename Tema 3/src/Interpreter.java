import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Gabriel Badila
 * 
 *         <p>
 *         In aceasta clasa are loc aproape toata implementarea temei.
 *         </p>
 *         
 *         <p>
 *         Aici se realizeaza parcurgerea fisierelor de intrare si crearea fisierelor de iesire.
 *         <p>
 */

public class Interpreter {

	public Node ProgramNode;
	public ActNode ActNode;
	public SceneNode SceneNode;
	public AssignmentNode AssignmentNode;
	public LvalNode LvalNode;
	public RvalNode RvalNode;
	public ConstantNode ConstantNode;
	public OperatorNode OperatorNode;
	public OutputNode OutputNode;

	public ArrayList<Character> vector = new ArrayList<Character>();
	public ArrayList<String> operation = new ArrayList<String>();
	public ArrayList<String> results = new ArrayList<String>();;

	
	/**
	 * <p>
	 * Constructorul clasei
	 * </p>
	 */
	Interpreter() {
		ProgramNode = new Node("ProgramNode");
		ActNode = new ActNode("ActNode");
		SceneNode = new SceneNode("SceneNode");
		AssignmentNode = new AssignmentNode("AssignmentNode");
		LvalNode = new LvalNode("LvalNode");
		RvalNode = new RvalNode("RvalNode");
		ConstantNode = new ConstantNode("ConstantNode");
		OperatorNode = new OperatorNode("OperatorNode");
		OutputNode = new OutputNode("OutputNode");
	}

	/**
	 * <p>
	 * In aceasta metoda au loc operatii cu doua numere.
	 * </p>
	 * 
	 * <p>
	 * Aceasta metoda este apelata in cadrul metodei "Reverse".
	 * </p>
	 * 
	 * @param type
	 * 			- tipul operatiei
	 * @param a
	 * 			- primul numar
	 * @param b
	 * 			- al doilea numar
	 * @return	rezultatul operatiei dintre cele doua numere
	 */
	public int OperationI(String type, int a, int b) {
		switch (type) {
		case "sum":
			return a + b;
		case "difference":
			return a - b;
		case "product":
			return a * b;
		default:
			return a / b;
		}
	}

	/**
	 * <p>
	 * In aceasta metoda au loc operatii cu un singur numar.
	 * </p>
	 * 
	 * <p>
	 * Aceasta metoda este apelata in cadrul metodei "Reverse".
	 * </p>
	 * 
	 * @param type
	 * 			- tipul operatiei
	 * @param a
	 * 			- numarul ce va fi folosit in cadrul operatiei
	 * @return	rezultatul operatiei aplicate numarului
	 */
	public int OperationII(String type, int a) {
		switch (type) {
		case "square":
			return a * a;
		default:
			return a * a * a;
		}
	}

	/**
	 * <p>
	 * In aceasta clasa este utilizate o stiva pentru a afisa rezultatul operatiilor.
	 * Operatiile au loc pe un sir de numere si operatori aflati sub forma poloneza prefixata.
	 * </p>
	 * 
	 * @param operation
	 * 					- vectorul de operatori si operanzi
	 * @param type
	 * 					- scriere zecimala sau ASCII
	 * @param output2
	 * 					- fisierul de output al rezultatelor
	 */
	void Reverse(ArrayList<String> operation, String type, String output2) {

		int a = 0;
		int b = 0;
		int val = 0;

		Stack<Integer> reverse = new Stack<Integer>();
		
		for (int i = operation.size() - 1; i >= 0; i--) {
			
			// elementul din sir este un operator ce lucreaza cu doua numere
			if (operation.get(i).equals("sum") || operation.get(i).equals("difference")
					|| operation.get(i).equals("product") || operation.get(i).equals("divided")) {
				a = reverse.pop();
				b = reverse.pop();
				a = OperationI(operation.get(i), a, b);
				reverse.push(a);
				
				// elementul din sir este un operator ce lucreaza cu un singur numar
			} else if (operation.get(i).equals("square") || operation.get(i).equals("cube")) {
				a = reverse.pop();
				a = OperationII(operation.get(i), a);
				reverse.push(a);
				
				// elementul din sir este fie un nume de caracter caruia i se atribuie o anumita 
				// valoare, fie un numar
			} else {
				boolean check = false;
				for (int j = 0; j < vector.size(); j++) {
					if (operation.get(i).equals(vector.get(j).getNume())) {
						val = vector.get(j).getValue();
						check = true;
					}
				}
				if (check == true)
					reverse.push(val);
				else
					reverse.push(Integer.parseInt(operation.get(i)));
			}

		}

		val = reverse.pop();
		
		// verific daca numarul trebuie afisat in zecimal sau in ASCII
		if (type.equals("Open"))
			results.add("" + val);
		else
			results.add("" + (char) val);

	}

	/**
	 * <p>
	 * Metoda de verificare daca un nod mai poate avea copii sau nu.
	 * Nodul operator poate avea unul sau doi copii.
	 * </p>
	 * 
	 * @param Op
	 * 			- nod operator (matematic)
	 * @return	urmatorul nod operator parinte valabil
	 */
	OperatorNode verificare(OperatorNode Op) {
		if (Op.getOper() == 1 && Op.getName().equals("SquareNode")
				|| Op.getName().equals("CubeNode")) {
			if (!Op.getParent().getName().equals("AssignmentNode")) {
				Op = (OperatorNode) Op.getParent();
				Op = verificare(Op);
			}
		} else if (Op.getOper() == 2 && !Op.getName().equals("SquareNode")
				&& !Op.getName().equals("CubeNode")) {
			if (!Op.getParent().getName().equals("AssignmentNode")) {
				Op = (OperatorNode) Op.getParent();
				Op = verificare(Op);
			}
		}

		return Op;
	}

	/**
	 * <p>
	 * Parcurgerea textului unui personal.
	 * </p>
	 * 
	 * <p>
	 * Replicile unui personaj sunt salvate (sub forma de token-uri) in vectorul text pentru a 
	 * putea fi prelucrate.
	 * </p>
	 * 
	 * @param pers
	 * 			- personajul care spune replica
	 * @param names
	 * 			- personajele prezente in scena
	 * @param text
	 * 			- textul propriu-zis salvat sub forma token-urilor
	 * @param output2
	 * 			- fisierul de output al rezultatelor
	 */
	void Parcurgere_text(String pers, ArrayList<String> names, ArrayList<Parser.Token> text,
			String output2) {

		int count = 0;
		int number = 0;
		int noun = 0;
		boolean operator = false;
		boolean pers_exist = false;
		String rez = null;
		
		// folosesc acest vector pentru a salva OperatorNode-ul utilizat cu un pas in urma
		OperatorNode Aux = new OperatorNode("OperatorNode");

		for (int i = 0; i < names.size(); i++)
			vector.add(new Character(names.get(i)));
		
		// instantiez LvalNode si OutputNode
		if (names != null && names.size() > 1) {
			if (pers.equals(names.get(0))) {
				LvalNode = new LvalNode("LvalNode " + names.get(1));
				OutputNode = new OutputNode("OutputNode " + names.get(1));

				rez = names.get(1);
			} else if (pers.equals(names.get(1))) {
				LvalNode = new LvalNode("LvalNode " + names.get(0));
				OutputNode = new OutputNode("OutputNode " + names.get(0));

				rez = names.get(0);
			}
		}

		// parcurg textul propozitie cu propozitie pana il termin
		while (count < text.size()) {
			while (text.get(count).type != TYPE.terminator) {
				
				// setez AssignmentNode
				if (text.get(count).type == TYPE.second_person) {
					pers_exist = true;
					AssignmentNode = new AssignmentNode("AssignmentNode");
					SceneNode.setChildren(AssignmentNode);
					AssignmentNode.setCount(SceneNode.getCount() + 1);
					AssignmentNode.setChildren(LvalNode);
					LvalNode.setCount((AssignmentNode.getCount() + 1));
					operation.clear();
				}

				// setez RvalNode
				if (text.get(count).type == TYPE.first_person_reflexive) {
					if (pers.equals(names.get(0))) {
						RvalNode = new RvalNode("RValNode " + names.get(0));
						operation.add(names.get(0));
					} else if (pers.equals(names.get(1))) {
						RvalNode = new RvalNode("RValNode " + names.get(1));
						operation.add(names.get(1));
					}
					Aux.setChildren(RvalNode);
					RvalNode.setCount((Aux.getCount() + 1));
					Aux.setOper(Aux.getOper() + 1);
				} else if (text.get(count).type == TYPE.second_person_reflexive) {
					if (pers.equals(names.get(0))) {
						RvalNode = new RvalNode("RValNode " + names.get(1));
						operation.add(names.get(1));
					} else if (pers.equals(names.get(1))) {
						RvalNode = new RvalNode("RValNode " + names.get(0));
						operation.add(names.get(0));
					}
					Aux.setChildren(RvalNode);
					RvalNode.setCount((Aux.getCount() + 1));
					Aux.setOper(Aux.getOper() + 1);
				} else if (text.get(count).type == TYPE.character) {
					if (operator == false) {
						RvalNode = new RvalNode("RValNode " + text.get(count).value);
						AssignmentNode.setChildren(RvalNode);
						RvalNode.setCount((AssignmentNode.getCount() + 1));
						operation.add(text.get(count).value);
					} else {
						RvalNode = new RvalNode("RValNode " + text.get(count).value);
						Aux.setChildren(RvalNode);
						RvalNode.setCount((Aux.getCount() + 1));
						Aux.setOper(Aux.getOper() + 1);
						operation.add(text.get(count).value);
					}
				}
				
				// numar adjectivele
				if (text.get(count).type == TYPE.positive_adjective
						|| text.get(count).type == TYPE.negative_adjective
						|| text.get(count).type == TYPE.neutral_adjective)
					number++;

				// daca a fost creat AssignmentNode-ul, deci se vorbeste despre o persoana, 
				// calculez constantele
				if (pers_exist == true) {
					if (text.get(count).type == TYPE.positive_noun
							|| text.get(count).type == TYPE.neutral_noun) {
						noun = 1;
						if (number > 0)
							noun = (int) (noun * Math.pow(2, number));
						operation.add("" + noun);
						ConstantNode = new ConstantNode("ConstantNode " + noun);
						if (operator == false) {
							AssignmentNode.setChildren(ConstantNode);
							ConstantNode.setCount(AssignmentNode.getCount() + 1);
						} else {
							Aux.setChildren(ConstantNode);
							ConstantNode.setCount(Aux.getCount() + 1);
							Aux.setOper(Aux.getOper() + 1);
							Aux = verificare(Aux);
						}

						for (int i = 0; i < vector.size(); i++)
							if (vector.get(i).getNume().equals(rez)
									&& ConstantNode.getParent().getName().equals("AssignmentNode"))
								vector.get(i).setValue(noun);

						number = 0;
						noun = 0;
					} else if (text.get(count).type == TYPE.negative_noun) {
						noun = -1;
						if (number > 0)
							noun = (int) (noun * Math.pow(2, number));
						operation.add("" + noun);
						ConstantNode = new ConstantNode("ConstantNode " + noun);
						if (operator == false) {
							AssignmentNode.setChildren(ConstantNode);
							ConstantNode.setCount(AssignmentNode.getCount() + 1);
						} else {
							Aux.setChildren(ConstantNode);
							ConstantNode.setCount(Aux.getCount() + 1);
							Aux.setOper(Aux.getOper() + 1);
							Aux = verificare(Aux);

						}

						for (int i = 0; i < vector.size(); i++)
							if (vector.get(i).getNume().equals(rez)
									&& ConstantNode.getParent().getName().equals("AssignmentNode"))
								vector.get(i).setValue(noun);

						number = 0;
						noun = 0;
					}
				}
				
				// instantiez OperatorNode-urile
				if (text.get(count).type == TYPE.math) {
					operation.add(text.get(count).value);
					String op = text.get(count).value;
					op = op.substring(0, 1).toUpperCase() + op.substring(1);

					if (operator == false) {
						OperatorNode = new OperatorNode(op + "Node");
						AssignmentNode.setChildren(OperatorNode);
						OperatorNode.setCount(AssignmentNode.getCount() + 1);
						Aux = OperatorNode;
						operator = true;
					} else {
						OperatorNode = new OperatorNode(op + "Node");
						Aux.setChildren(OperatorNode);
						OperatorNode.setCount(Aux.getCount() + 1);
						Aux.setOper(Aux.getOper() + 1);

						if (Aux.getOper() == 1 && Aux.getName().equals("SquareNode")
								|| Aux.getName().equals("CubeNode")) {
							if (OperatorNode.getOper() == 0)
								Aux = OperatorNode;
							else if (!Aux.getParent().getName().equals("AssignmentNode"))
								Aux = (OperatorNode) Aux.getParent();
						} else if (Aux.getOper() == 2 && !Aux.getName().equals("SquareNode")
								&& !Aux.getName().equals("CubeNode")) {
							if (OperatorNode.getOper() == 0)
								Aux = OperatorNode;
							else if (!Aux.getParent().getName().equals("AssignmentNode"))
								Aux = (OperatorNode) Aux.getParent();
						} else {

							Aux = OperatorNode;

						}

					}
					number = 0;
					noun = 0;
				}
				
				// apelez metoda Reverse pentru a afisa rezultatele
				if (text.get(count).value.equals("Speak") || text.get(count).value.equals("Open")) {
					String type = text.get(count).value;
					count++;
					if (text.get(count).type == TYPE.second_person_possessive) {
						SceneNode.setChildren(OutputNode);
						OutputNode.setCount(SceneNode.getCount() + 1);
						String obj = "" + output2.charAt(11);
						if (Integer.parseInt(obj) <= 7 && Integer.parseInt(obj) > 1)
							Reverse(operation, type, output2);
					}

				}

				count++;
			}

			pers_exist = false;
			operator = false;
			count++;
		}
	}

	/**
	 * <p>
	 * In aceasta metoda are loc parsarea si crearea salvarea replicilor personajelor intr-un vector.
	 * </p>
	 * 
	 * @param parser
	 * 				- realizeaza parsarea
	 * @param token
	 * 				- element al parsarii
	 * @param output
	 * 				- fisierul de output pentru arbore
	 * @param output2
	 * 				- fisierul de output al rezultatelor
	 * @throws FileNotFoundException
	 * 				- exceptie in caz ca un fisier nu este gasit
	 */
	void parse(Parser parser, Parser.Token token, String output, String output2)
			throws FileNotFoundException {

		TreeVisitor p = new TreeVisitor();
		p.OpenFile(output2);

		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Parser.Token> text = new ArrayList<Parser.Token>();
		ArrayList<Parser.Token> text2 = new ArrayList<Parser.Token>();

		// daca piesa n-a ajuns la sfarsit
		while (token != null) {
			
			// instantiez ActNode-urile
			if (token.type == TYPE.keyword && token.value.equals("Act")) {
				token = parser.getNext();
				if (token.type != TYPE.terminator) {
					ActNode = new ActNode("ActNode " + token.value);
					ProgramNode.setChildren(ActNode);
					ActNode.setCount(ProgramNode.getCount() + 1);
				}
			}

			// instantiez SceneNode-urile
			if (token.type == TYPE.keyword && token.value.equals("Scene")) {
				token = parser.getNext();
				if (token.type != TYPE.terminator) {
					SceneNode = new SceneNode("SceneNode " + token.value);
					ActNode.setChildren(SceneNode);
					SceneNode.setCount(ActNode.getCount() + 1);
				}
			}
			
			// salvez personajele din scena si replicile lor
			if (token.value.equals("[")) {
				token = parser.getNext();
				if (token.value.equals("Enter")) {
					token = parser.getNext();
					while (!token.value.equals("]")) {
						if (token.type == TYPE.character)
							names.add(token.value);
						token = parser.getNext();
					}
				}

				token = parser.getNext();
				String pers = null;
				String pers2 = null;

				if (!token.value.equals("[")) {
					pers = token.value.substring(0, token.value.length() - 1);

					token = parser.getNext();

					int ok = 0;
					while (!token.value.equals("[")) {
						if (token.value.indexOf(":") >= 0) {
							pers2 = token.value.substring(0, token.value.length() - 1);
							token = parser.getNext();
							while (!token.value.equals("[")) {
								text2.add(token);
								token = parser.getNext();
							}
							ok = 1;
							break;
						}
						text.add(token);
						token = parser.getNext();
					}

					Parcurgere_text(pers, names, text, output2);
					if (ok == 1)
						Parcurgere_text(pers2, names, text2, output2);
				}

				token = parser.getNext();
				if (token.value.equals("Exit")) {
					token = parser.getNext();
					for (int i = 0; i < names.size(); i++)
						if (token.value.equals(names.get(i)))
							names.remove(i);

				} else if (token.value.equals("Exeunt")) {
					names.clear();

				}

				text.clear();
				text2.clear();
			}

			token = parser.getNext();
		}

		// afisarea propriu-zisa a rezultatelor
		for (int i = 0; i < results.size(); i++)
			p.getP().println(results.get(i));

		results.clear();
		p.CloseFile();

		p.OpenFile(output);
		// afisarea arborelui cu ajutorul TreeVisitor-ului
		ProgramNode.accept(p);
		p.CloseFile();

	}

	/**
	 * <p>
	 * Functia Main
	 * </p>
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String test;
		String output;
		String output2;
		String wordlist = "wordlists";

		// parcurg cele 10 fisiere de input, creez fisierele de output si instantiez: 
		//interpreter-ul, parser-ul si token-ul
		for (int i = 1; i <= 10; i++) {
			test = "tests/test" + i + ".spl";
			output = "output/test" + i + ".ast";
			output2 = "output/test" + i + ".out";
			Interpreter interpreter = new Interpreter();
			Parser parser = new Parser(wordlist, test);
			Parser.Token token = parser.getNext();
			interpreter.parse(parser, token, output, output2);

		}

	}
}
