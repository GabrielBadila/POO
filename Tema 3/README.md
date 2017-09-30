# Shakespeare Programming Language Interpreter(Romanian language)


	Programare Orientata pe Obiecte
	
	Tema 3 - Shakespeare Programming Language Interpreter
	
	Badila Gabriel Alin
	323 CA
	
	
	============================================================================
	
	Tema contine destul de multe clase.
	Majoritatea implementarii se afla in clasa Interpreter.
	In clasa Node sunt reprezentate toate caracteristicile unui nod. Aceasta 
	clasa este mostenita de multe altele: ActNode, SceneNode, AssignmentNode, 
	LvalNode, RvalNode, ConstantNode, OperatorNode, OutputNode.
	
	In Interpreter parcurg textul parsat si creez arborele, cu ajutorul 
	visitorului parcurg arborele.
	Pe langa arbore, in interpreter realizez si diferite operatii pe care le 
	scriu in fisierele de output. Aceste operatii se realizeaza de fiecare data
	cand in piesa apare unul din mesajele "Speak your mind" sau "Open your 
	heart".
	
	In TreeVisitor parcurg cu ajutorul visitorului intregul arbore sau anumite 
	bucati din el, iar apoi in scriu in fisier.
	
	
