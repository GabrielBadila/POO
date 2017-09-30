# Social-Network (Romanian language)
Simulate a social network using graphs in Java language


	Programare Orientata pe Obiecte
  
	Tema 1 - Social Network

	Badila Gabriel Alin
	323 CA


DESCRIERE

	Tema contine patru fisiere: User.java, Network.java, Main.java, 
	FileParser.java. 
	
	User.java si Network.java sunt in totalitate create de 
	mine. Main.java este doar editat (am realizat parsarea), iar FileParser.java
	este fisierul cu parsarea propriu-zisa, acesta fiind nemodificat.
	
	
	USER
	
	Clasa de baza, contine campuri ce retin informatiile utilizatorilor (ID, 
	Nume), campuri utilizate in diverse metode (vizitat, distanta) si un camp 
	de tip ArrayList, in care sunt salvati prietenii fiecarui utilizator. Mai 
	contine si doi constructori (cu si fara parametrii).
	
	
	NETWORK
	
	Clasa derivata din clasa User, aici sunt implementate toate metodele.
	Aici este implementat Pattern-ul Singleton (pentru o unica instanta a 
	clasei) si este declarat un camp de tip ArrayList, in care sunt salvati 
	utilizatorii.
	
	
	METODE
	
	1) Add - functia de adaugare a unui utilizator.
		
		Verifica dupa ID daca utilizatorul exista deja, iar daca acesta exista 
	este afisat mesajul "DUPLICATE". Daca utilizatorul nu exista, atunci se 
	doreste a fi adaugat pe o pozitie care sa nu strice ordinea id-urilor, 
	astfel trebuie verificate mai multe cazuri: nu mai exista niciun utilizator, 
	mai exista un singur utilizator in lista, exista mai multi utilizatori in 
	lista. Daca exista mai multi utilizatori atunci sunt puse trei conditii 
	pentru a putea fi adaugat pe pozitia corespunzatoare. Daca operatia s-a 
	realizat cu succes atunci va fi afisat mesajul "OK".
	
	2) Remove - functie de eliminare a unui utilizator.
	
		Verifica daca exista utilizatorul respectiv, daca exista, il gaseste 
	printre utilizatori, ii sterge relatiile de prietenie (Unfriend_Remove - voi 
	vorbi putin mai jos) si il elimina, afisand mesajul "OK", iar daca nu exista 
	afiseaza mesajul "INEXISTENT".
		
	3) Friend - stabileste o relatie de prietenie intre doi utilizatori.
	
		Verifica daca id-urile primite sunt egale, iar daca sunt, afiseaza direct 
	"INEXISTENT" si metoda se incheie. Daca id-urile sunt diferite atunci sunt 
	cautate. Daca cel putin unul dintre ele nu este gasit este afisat mesajul 
	"INEXISTENT", altfel se realizeaza relatie de prietenie, atat ID1 - ID2, 
	cat si ID2 - ID1, respectand ordinea id-urilor prietenilor (acelasi algoritm 
	ca la "Add") si se afiseaza mesajul "OK".
	
	4) Unfriend - sterge o relatie de prietenie dintre doi utilizatori.
		
		Metoda opusa metodei Friend, verifica daca cele doua id-uri sunt egale, 
	iar daca raspunsul este afirmativ va fi afisat mesajul "INEXISTENT". Daca 
	id-urile sunt diferite, vor fi cautate. Daca id-ul al cel putin unuia dintre 
	utilizatori nu este gasit va fi afisat mesajul "INEXISTENT", altfel relatia 
	de prieteni dintre cei doi utilizatori va fi stearsa, iar mesajul "OK" va fi 
	afisat.
	
	5) Unfriend_Remove - face acelasi lucru ca Unfriend, doar ca nu mai afiseaza 
	mesaje, este metoda apelata de "Remove".
	
	6) DFS - realizeaza o parcurgere in adancime a utilizatorilor.
	
		Primeste ca parametru un utilizator si realizeaza o parcurgere in 
	adancime pornind de la acel utilizator. Returneaza o lista cu id-urile 
	utilizatorilor gasiti prin aceasta parcurgere.
	
	7) BFS - realizeaza o parcurgere in latime a utilizatorilor.
	
		Primeste ca parametru un utilizator si dupa ce realizeaza o parcurgere 
	in latime pornind de la acel utilizator returneaza cea mai mare distanta 
	gasita (de la primul utilizator pana unde se opreste parcurgerea).
	
	8) Print_User - pe baza unui id primit, daca id-ul exista afiseaza numele, 
	id-ul si prietenii utilizatorului respectiv, altfel afiseaza "INEXISTENT".
	
	9) Print_Network - afiseaza numele, id-ul si prietenii fiecarui utilizator 
	din retea.
	
	10) Print_Communities - apeleaza metoda DFS si afiseaza fiecare comunitate 
	din retea.
	
	11) Print_Strength - afiseaza gradul de socializare al fiecarei comunitate 
	din retea.
	
		Pe baza unui id primit ca parametru este apelata metoda BFS. Pentru 
	fiecare id din lista returnata de DFS este apelat BFS si este salvata 
	distanta cea mai mare. Cu ajutorul formulei g = (N - max) * 100 / (N - 1) 
	este aflat si afisat gradul de socializare. Daca lista returnata de DFS 
	contine un singur id, gradul de socializare este 0, iar daca id-ul dat ca 
	parametru nu exista in retea este afisat mesajul "INEXISTENT".
	
	
	MAIN
	
	Instantiez un obiect de tip Network si apelez metoda "getInstance()". 
	Instantiez alt obiect de tip FileParser. Cu ajutorul celui de-al doilea 
	obiect instatiat apelez metoda "open" din FileParser, iar apoi cu ajutorul 
	altui obiect pe care il instantiez de tip String, parcurg fiecare linie din 
	fisierele pe care le citesc. Apoi apare etapa de verificare unde pun diverse 
	conditii pentru a afla ce metode trebuie sa apelez din clasa Network, iar in 
	cele din urma apelez metoda "close" din FileParser.
	
CONCLUZIE

	A fost o tema interesata si mi-a facut mare placere sa o implementez. Nu a 
	fost prea dificila si sper ca si celelalte teme sa fie in aceeasi maniera.
