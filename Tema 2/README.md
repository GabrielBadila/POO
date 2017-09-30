# Enigma Machine (Romanian language)
Simulate Enigma Machine functionality


	Programare Orientata pe Obiecte
	
	Tema 2 - Enigma
	
	Badila Gabriel Alin
	323 CA
	
	
	============================================================================
	
	Masina Enigma contine 6 clase: Main, Enigma, Rotor, RotorType, Reflector si 
	Plugboard.
	
	Am ales sa folosesc agregarea deoarece exista o relatie "has a", Enigma are 
	un Rotor, are un Reflector, are un Plugboard.
	
	Clasa Rotor are nevoie de clasa RotorType pentru a-si alege tipul rotorului.
	
	Clasele Plugboard si Reflector sunt destul de similare avand functionalitati 
	asemanatoare. As fi putut implementa functionalitatile lor intr-o singura 
	clasa, dar am vrut sa scot in evidenta componentele masinii Enigma.
	
	Cum este specificat si in enunt, clasele Rotor, Reflector si Plugboard 
	realizeaza criptarea literelor. Clasa Enigma combina functionalitatile 
	claselor anterioare si realizeaza criptarea propriu-zisa. Clasa Main 
	realizeaza citirea din fisier si apelarea metodei de criptare din Enigma.
	
	============================================================================
	
	Tema a fost foarte interesanta, iar la prima vedere nu credeam ca imi va 
	pune prea multe probleme.
	
	Lipsa unui exemplu de baza care sa arate toate functionalitatile posibile 
	ale masinii: rotirea inelelor, rotirea rotoarelor, cazul de double-stepping, 
	cazul in care alfabetul masinii este un subsir din alfabetul normal, precum 
	si aparitia foarte tarzie a testelor si a checker-ului a pus destule 
	probleme.
	
	La implementare singurele probleme pe care le-am avut au fost cazul de 
	double-stepping al rotorului din mijloc si iesirile din zona [0, 25] in 
	cazul codurilor literelor alfabetului.
	Un posibil impediment ar putea fi considerat faptul ca checkerul nu 
	recunoaste functia "Math.floorMod", asa ca la implementare atunci cand 
	masina ajungea pe o pozitia negativa nu am avut alta alegere decat sa adun 
	26.
	
	============================================================================
	
	Masina implementeaza modelul pipeline pentru ca fiecare variabila returnata 
	de o etapa de criptare este preluata de etapa de criptare imediat urmatoare.
	
	In cazul in care in urma unei operatii de criptare rezulta o litera ce nu 
	face parte din alfabet se va realiza operatia de "continue LoopName", unde 
	"LoopName" este o eticheta a for-ului de parcurgere a textului.
	
	Masina Enigma implementata functioneaza pe cazul cu 3 rotoare, dar nu este 
	nicio problema in a functia pe n rotoare pentru ca implementarea realizata 
	permite acest lucru.

	In concluzie, cred ca am realizat o masina Enigma complet functionala 
	conform enuntului temei.
