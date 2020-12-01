package domini.model;

import java.util.*;
import java.io.*;

public class Tablero {

		//Atributs de la classe
			
			private Cella[][] taulell;
			
			private int numBlanques;
			
			/**
			    * Constructora del Tablero
			    * 	   
			    * 
			    * */
			public Tablero() {}
			
			/**
			    * Constructora de Tablero amb el taulell i el nombre de cel.les blanques
			    * @param taulell el taulell
			    * 
			    */
			public Tablero(Cella[][] taulell)
			{
				this.taulell = taulell;
				this.numBlanques = -1;
			}
			
			/**
			    * Funcio que assigna els valors de les celles negres
			    * <p>Es recorre tot el taulell i assigna valors a les celles negres amb una funci especfica</p>
			    * @see #assigN(int, int)
			    * 	   
			    * */
			public void assignarNegres() {
				int midaF = taulell.length;
				int midaC = taulell[0].length;
				for (int i = 0;i< midaF ;++i) {
					for (int j =0; j < midaC; j++) {
						if (taulell[i][j].esNegra()) {
							assigN(i,j);
							
						}
					}
				}
			}
			
			/**
			    * Funcio que assigna els valors d'una unica cel.la negra
			    * 
			    * @param i posicio i de la cel.la negra
			    * @param j posicio j de la cel.la negra   
			    * */
			public void assigN(int i,int j) {
				int sum = 0;
				int ii = i+1;
				while (ii < taulell.length && !taulell[ii][j].esNegra()) {
					sum += ((CellaBlanca)taulell[ii][j]).getValorBlanca();
					++ii;
				}
				if (sum != 0) {
					((Negra)taulell[i][j]).setValorEsq(sum);
					sum = 0;
				}
				int jj = j+1;
				while (jj < taulell[0].length && !taulell[i][jj].esNegra()) {
					sum += ((CellaBlanca)taulell[i][jj]).getValorBlanca();
					++jj;
				}
				
				if (sum != 0) {
					((Negra)taulell[i][j]).setValorDreta(sum);
				}
				
			}
			
			/**
			    * Funcio que genera els numeros del kakuro
			    * <p>Es plena les cel.les blanques de taulell amb nombres que compleixin les normes</p>
			    * 
			    * @return tot el taulell generat
			    * @see #btGen(Cella[][], int, int)
			    * */
			
			public boolean generarNumFilCol(){
				return btGen(taulell, 1, 1);
			}
			
			/**
			    * Funcio que omple les cel.les blanques per fer un possible kakuro
			    * @param c Array de cel.les que referencia al taulell
			    * @param i Fila numero i 
			    * @param j Columna numero j
			    * @return <code>true</code> si en cada crida recursiva el valor assignat es correcte per a la seva fila i columna
			    * <code>false</code> en cas contrari
			    * 
			    * 
			    * */
			

			public boolean btGen(Cella[][]c, int i, int j){
				int midaF = c.length;
				int midaC = c[0].length;
				if (j == midaC && i == midaF-1) {
						
						return true;
				}

				if (j == midaC){
					i++;
					j = 0;
				}
				if (c[i][j].esNegra()){
			           	return btGen(c, i, j + 1);
					
				}

				else  {
					int[] combo = getMyCombos();
					for(int k = 0; k < 9; k++){
					   	if (esCorrecteFila(c,i,j,combo[k]) && esCorrecteCol(c, i, j, combo[k]))     {           
							((CellaBlanca)c[i][j]).setBlanca(combo[k]);
							if(btGen(c,i,j+1)) return true;
						}
						((CellaBlanca)c[i][j]).setBlanca(-1);
					}

				}
				
				return false;

			}
			
			/**
			    * Funcio que retorna combinacions possibles de nombres per insertar a una cel.la blanca.
			    * 
			    * @return <code>combo</code> Array amb una combinacio random de nombres que poden ser assignats a cada cel.la blanques.
			    * */
			
			public int[] getMyCombos() {
				int[] combo = new int[9];
				boolean[] used = new boolean[10];
				Arrays.fill(used, false);
				for(int i = 0; i < 9;i++) {
					Random num = new Random();
					int valor = num.nextInt(10);
					while (valor < 1 || used[valor]) valor = num.nextInt(10);
					combo[i] = valor;
					used[valor] = true;
				}
				return combo;
			}
			
			/**
			    * Funcio que comprova que el nombre "k" sigui valid dins la fila
			    * 
			    * @param c taulell
			    * @param i posicio i del taulell
			    * @param j posicio j del taulell
			    * @param k nombre possible
			    * @return <code>true</code> es correcte dins la fila
			    * <code>false</code> no es correcte
			    * */
			public boolean esCorrecteFila(Cella[][] c, int i, int j, int k){
					int it = j+1;
					while(it < c[0].length && !c[i][it].esNegra())
					{
						if(((CellaBlanca)c[i][it]).getValorBlanca() == k) return false;
						++it;
					}
					it = j-1;
					while(it > 0 && !c[i][it].esNegra())
					{
						if(((CellaBlanca)c[i][it]).getValorBlanca() == k) return false;
						--it;
					}
			
				return true;
			}
			
			/**
			    * Funcio que comprova que el nombre "k" sigui valid dins la columna
			    * 
			    * @param c taulell
			    * @param i posicio i del taulell
			    * @param j posicio j del taulell
			    * @param k nombre possible
			    * @return <code>true</code> es correcte dins la columna
			    * <code>false</code> no es correcte
			    * */
			public boolean esCorrecteCol(Cella[][] c,int i, int j, int k){
				
					boolean okay = true;
					int it = 1;
					boolean acabatUp = false;
					boolean acabatDown = false;
					while (!acabatUp || !acabatDown) {
						
						if(i+it == c.length) acabatDown = true;
						if(i-it == -1) acabatUp = true;
						
						if (!acabatDown && c[i+it][j].esNegra()) acabatDown = true;
						if (!acabatUp && c[i-it][j].esNegra()) acabatUp = true;
			
			
						if (!acabatDown && ((CellaBlanca)c[i+it][j]).getValorBlanca() == k){
							acabatDown = true;
							okay = false;
			
						}
			
						if (!acabatUp && ((CellaBlanca)c[i-it][j]).getValorBlanca() == k){
							acabatUp = true;
							okay = false;
			
						}
			
						++it;
			
					}
			
					return okay;

			}
			/**
			    * Funcio que ens retorna si la cella pot ser negra segons les normes del joc Kakuro.
			    * 
			    * @param taulell taulell
			    * @param i posicio i del taulell
			    * @param j posicio j del taulell
			    * 
			    * @return <code>candidat</code> Cert si pot ser una cel la negra. Fals si ha de ser blanca per complir les regles.
			    * */


			
			public boolean possibleNegra (Cella[][] taulell,int i,int j) {
				
				
				boolean candidat = true;
				if(i-2>=0 && !taulell[i-1][j].esNegra() && taulell[i-2][j].esNegra()) candidat = false;
				
				if(i+2 < taulell.length && !taulell[i+1][j].esNegra() && taulell[i+2][j].esNegra()) candidat = false;
				
				if(j-2>=0 && !taulell[i][j-1].esNegra() && taulell[i][j-2].esNegra()) candidat = false;
				
				if(j+2 < taulell[0].length  && !taulell[i][j+1].esNegra() && taulell[i][j+2].esNegra()) candidat = false;
				
				
				if (j == taulell[0].length-2 && !taulell[i][j+1].esNegra()) candidat = false;
				
				if (i == taulell.length-2 && !taulell[i+1][j].esNegra()) candidat = false;
				
				
				return candidat;
			}
			
			
			/**
			    * Funcio que genera el Kakuro.
			    * 
			    * <p>Primer, crea un taulell kakuro unicament amb celles negres.
			    * Despres, comprova que aquest sigui valid.(Probabilitat de que no sigui valid relativament baixa)
			    * Finalment, assigna els valors a les cel.les negres.
			    * </p>
			    * 
			    * */
			
			public void creaKakuroByNegres() {
				Random num = new Random();
				int midaF = num.nextInt(10);
				while(midaF < 3) midaF = num.nextInt(10);
				
				int midaC = num.nextInt(10);
				while(midaC < 3) midaC = num.nextInt(10);
				
				boolean tagucci = false;
				boolean numeros = false;
				while (!tagucci || !numeros) {
					
					taulell  = new Cella[midaF][midaC];
					Cella[][] c = taulell;
					iniCreadorKakuroNegres(c);
					
					
					
					numBlanques = (taulell.length-1)*(taulell[0].length-1);
					for (int i=1; i <= midaF/2; i++) {
						for (int j =1; j < midaC; j++) {
							if (possibleNegra(c,i,j)) {
								int bl = num.nextInt(2);
								if(bl == 0) {
									taulell[i][j] = new Negra();
									taulell[taulell.length-i][taulell[0].length-j] = new Negra();
									numBlanques -= 2;
								}
							}
						}
					}
					
					tagucci = (comprovaTaulell(numBlanques) && comprovaBlanques());
					if(taulell.length > 10) {
						tagucci = tagucci && mesDeNou();
					}
					
					numeros = generarNumFilCol();
					
				}
				assignarNegres();
				
			}
			
			/**
			    * Funcio que comprova que no hagin mes de 9 cel.les en una fila o columna.
			    * 
			    * @return <code>okay</code> no hi ha mes de 9 cel.les en una fila o columna.
			    * 
			    * */
			
			public boolean mesDeNou(){
						int comp = 0;
						boolean okay = true;
						for(int fil  = 0; fil < taulell.length && okay; ++fil){
							for (int i = 0; i < taulell[0].length && okay; ++i){
									if(!taulell[fil][i].esNegra()) ++comp;
									else comp = 0;
				
									if (comp > 9) okay = false;
							}
							comp = 0;
						}
				
						comp = 0;
						for(int col  = 0; col < taulell[0].length && okay; ++col){
							for (int i =0; i < taulell.length && okay;i++){
						
								if(!taulell[i][col].esNegra()) ++comp;
								else comp =0;
				
								if (comp > 9) okay = false;
				
							}
						comp = 0;
						}
				
						return okay;
			}


		
		/**
		    * Funcio que comprova que el graf generat a partir de les cel.les blanques del taulell sigui connex
		    * <p>
		    * Aquesta funcio fa servir una cerca en amplada, BFS, per comprovar que el cami entre totes les cel.les blanques sigui connex.</p>
		    * @param numBlanques Nombre de cel.les blanques existents al taulell.
		    * @return <code>true</code> si despres de fer el BFS, el graf es connex. 
		    *  	<code>false</code> si el BFS troba que el graf no es connex.
		    */       
		public boolean comprovaTaulell(int numBlanques)
		{
			int blanquesarbre = 0; //num blanques total connexes
			int midaF = taulell.length;
			int midaC = taulell[0].length;
			int Fila; //it1
			int Col; //it2
					
			boolean[][] vist = new boolean[midaF][midaC]; // mat visitats
			for (int i = 0; i < midaF;++i) {
					Arrays.fill(vist[i], false);
			}
					
			Cella c = taulell[1][1];	
						
			for (int i = 1; i < midaF; i++){
				for (int j =1; j < midaC; j++){
							
					if(!taulell[i][j].esNegra()) {
						c = taulell[i][j];
					}
				}
			}
			
			Queue<Cella> cola = new LinkedList<>();
			cola.add(c);
						
			while(!cola.isEmpty()){
								
				Cella aux = cola.remove();	
				Fila = aux.getPosX();
				Col = aux.getPosY();
							
				if(!aux.esNegra() && !vist[Fila][Col])
				{				
					++blanquesarbre;
					if( Fila - 1 >= 0 && !vist[Fila - 1][Col]) cola.add(taulell[Fila - 1][Col]); // cella de dalt
					if( Col - 1 >= 0 && !vist[Fila][Col-1]) cola.add(taulell[Fila][Col - 1]); // cella esquerra
					if(Fila+1 < midaF && !vist[Fila + 1][Col]) cola.add(taulell[Fila + 1][Col]); // cella dreta
					if(Col+1 < midaC && !vist[Fila][Col + 1]) cola.add(taulell[Fila][Col + 1]); // cella abaix
				}
				vist[Fila][Col] = true;
								
			}
				
			return (numBlanques == blanquesarbre);
					
		}
		
		
		/**
		    * Funcio que obte la primera cel.la blanca existent al taulell, comencant a la posicio (1,1) del taulell
		    * @return Primera cel.la blanca del taulell
		*/	
		public Cella primeraBlanca() {
			//System.out.print("PrimerBlanca\n");
			for (int i = 1; i < taulell.length;i++){
				for (int j =1; j < taulell[0].length;j++){		
					if(!taulell[i][j].esNegra()) {	
						return taulell[i][j];
					}
				}
			}
			return taulell[1][1];
		
		}

		/**
		    * Comprova que no quedin cel.les blanques incorrectes despres de la creacio del taulell.
		    * @return <code>true</code> si la creacio ha sigut satisfactoria
		    *  	<code>false</code> si hi ha alguna cel.la blanca insertada incorrectament.
		    */	
		public boolean comprovaBlanques() {
				boolean okay = true;
				for (int i = 1; i < taulell.length && okay;i++){
					for(int j = 1; j<taulell[0].length && okay;j++){
						if (taulell[i][j].esNegra()){
							if (j < taulell[0].length-2){
								if( !taulell[i][j+1].esNegra() && taulell[i][j+2].esNegra())  okay = false;
							}
							if (j == taulell[0].length-2) {
								if(!taulell[i][j+1].esNegra()) okay = false;
							}
							if (i < taulell.length-2){
								if(!taulell[i+1][j].esNegra() && taulell[i+2][j].esNegra())  okay = false;
							}
							if (i == taulell.length-2) {
								if(!taulell[i+1][j].esNegra()) okay = false;
							}
						}
					}
				}
				return okay;
		}
		
		/**
		    * Funcio que inicialitza el taulell per poder treballar amb ell mes endavant
		    *
		    * @param c Array de cel.les que referencia el taulell
		    */
		public void iniCreadorKakuroNegres(Cella[][] c)
		{
			for(int i = 0; i < c.length; ++i){
					c[i][0] = new Negra(i,0,-1,-1);
			}
			
			for(int j = 0; j < c[0].length; j++) c[0][j] = new Negra(0,j,-1,-1);
			
			for (int i=1;i < c.length;i++) {
				for (int j =1; j < c[0].length;j++) {
					c[i][j] = new CellaBlanca(-1,i,j);
				}
			}
		}

		
		/**
		    * Funcio que obte el taulell del Kakuro.
		    * @return Array de cel.les que representa el taulell del Kakuro.
		    */
		public Cella[][] getTaulell(){
			return taulell;
		}
		
		/**
		    * Assigna a la variable privada taulell l'array A de cel.les
		    *
		    * @param A Array de cel.les
		    */
		public void setTaulell(Cella[][] A) {
			taulell = A;
		}
		
		public int getNumBlanques() { return this.numBlanques;}
		       
		/**
		    * Funcio que imprimeix un taulell per pantalla
		    *
		    */
	    public void print(){
	          int midaF = taulell.length;
	          int midaC = taulell[0].length;
	          //String mapa[][] = new String[midaF][midaC*2];
	          for (int i = 0; i < midaF; i++) {
	        	  
	        	  for (int j = 0; j < midaC; j++) {        		  	

	        		  	if (taulell[i][j].esNegra()) {
	                	   
	        		  		Negra r = (Negra)taulell[i][j]; 
	        		  		if (r.getValorDreta() != -1 && r.getValorEsq() != -1)
	        		  		{  
	        		  		
	                    	   System.out.print("C");//cout << 'C' << r.getValorEsq() << 'F' << r.getValorDreta();
	                    	   System.out.print(r.getValorEsq());
	                    	   System.out.print("F");
	                    	   System.out.print(r.getValorDreta());
	                        }
	                        else if (r.getValorDreta() == -1 && r.getValorEsq() != -1)
	                        {
	                        
	                            System.out.print("C");//cout << 'F' << r.getValorDreta();
	                            System.out.print(r.getValorEsq());
	                        }
	                        else if (r.getValorDreta() != -1 && r.getValorEsq() == -1)
	                        {
	                        
	                        	System.out.print("F"); //cout << 'C' << r.getValorEsq();
	                            System.out.print(r.getValorDreta());
	                        }
	                        else {
	                        	//sortida = "*";
	                        	System.out.print("*");//else cout << '*';
	                        }
	                        	
	                    }
	                    else {
	                    	
	                        if (((CellaBlanca)taulell[i][j]).getValorBlanca() == -1) 
	                        {
	                        	
	                        	System.out.print("?");//cout << '?';
	                        }
	                        else 
	                        {
	                        	
	                        	System.out.print(((CellaBlanca)taulell[i][j]).getValorBlanca());//cout << r.getValorBlanca();
	                        } 
	    
	                    }
	                    
	                    if (j < midaC - 1) {
	                    
	                    	System.out.print(","); //cout << ',';
	                    }
	                   
	    
	                }
	               
	                System.out.print("\n");
	            }
	          	
	          	
	     }
	    

		
	    
	    /**
		    * Funcio que permet a l'usuari introduir un taulell per ser resolt.
		    * 
		    * @param br BufferedReader amb l'input
		    */
	   
	    public void introduirTab(BufferedReader br) {
	        int numFil, numCol;
	        System.out.println("Introdueix el tamany de files i columnes\n");
	        
	        try {
	        	
	        	//Control de l'input de files i columnes
		        String linea = br.readLine();
		 
		        char[] auxfc = linea.toCharArray();
		        
		        
		        int itt = 0;
		        numFil = Character.getNumericValue(auxfc[itt]);
		        ++itt;
		        while(auxfc[itt] != ',') {
		        	numFil = numFil*10 + Character.getNumericValue(auxfc[itt]);
		        	++itt;
		        }
		        ++itt;
		        numCol = Character.getNumericValue(auxfc[itt]);
		        while(itt+1 < auxfc.length) {
		        	numCol = numCol*10 + Character.getNumericValue(auxfc[itt+1]);
		        	++itt;
		        }
		        
		        
		        
		        System.out.println("Introdueix el teu kakuro a resoldre\n");
		       
		        //Codi que omple el taulell amb els valors introduits per l'input assignat
		        taulell  = new Cella[numFil][numCol];
		        for (int i = 0; i < numFil; i++) {
		        		String e = br.readLine();
		        		
		        		char[] aux = e.toCharArray();
		        		int it = 0;
		        		int pos = 0;
		        		while (it < aux.length) {
		        			if (aux[it] == '?') {		
		        				taulell[i][pos] = new CellaBlanca();
		        				it = saltar(it,aux);	
		        			}
		        			else if (aux[it] == '*') {
		        				taulell[i][pos] = new Negra();
		        				it = saltar(it,aux);
		        			}
		        			else if ((aux[it] == 'C' && it + 2 < aux.length && aux[it + 2] == 'F') || (aux[it] == 'C' && it + 3 < aux.length && aux[it + 3] == 'F')) {
		
		        				if((aux[it] == 'C' && it + 2 < aux.length && aux[it + 2] == 'F'))taulell[i][pos] = new Negra(retornaNum(aux, it + 3), retornaNum(aux, it + 1));
		        				else taulell[i][pos] = new Negra(retornaNum(aux, it + 4), retornaNum(aux, it + 1));    			        				
		        				it = saltar(it,aux);       				
		        			}
		        			else if (aux[it] == 'F') {
		        				
		        				taulell[i][pos] = new Negra(retornaNum(aux, it + 1), -1);
		        				it = saltar(it,aux);
		        				
		        			}
		        			else if (aux[it] == 'C') {
		        				
		        				taulell[i][pos] = new Negra(-1, retornaNum(aux, it + 1));
		        				it = saltar(it,aux);
		        			
		        			}
		        			
		        			pos++;
		        			
		        		}
		        		System.out.print("\n");
		        	}
		        	
	        }
	        catch (Exception e) {
	        		System.out.println(e.getMessage());
	        }
	    }


	        
	     
	     /**
		    * Transforma l'input inicial de chars del taulell fet per la font externa a enters. 
		    *
		    * @param s Array de chars.
		    * @param i Posicio actual a l'array.
		    * @return <code>result</code> amb la transformacio del char a int
		  
		    */       
	       public int retornaNum(char[] s,int i){
	        	int result = 0;
	        	result = s[i] - '0';
	        	if (i+1 < s.length && s[i+1] >= '0' && s[i+1] <= '9'){
	        		result = (result * 10) + (s[i + 1] - '0');
	        	}
	        	return result;
	        }
	       
	       
	       
	       /**
		    * Determina si s'ha d'efectuar un salt d'iterador dins l'array de chars, i retorna la posicio on cal anar.
		    *
		    * @param i iterador del possible salt
		    * @param A Array de celles
		    * @return <code>ret</code> posicio del salt dins l'array de chars
		    */
		
	       public int saltar(int i,char[] A) {
	    	   int ret = i;
	    	   while(ret < A.length && A[ret]!= ',') {
	    		   ret++;
	    	   }
	    	   if(i == A.length)return ret;
	    	   else return ret+1;
	       }
	       
	       /**
	        * Esborra tots els valors de les cel.les blanques del taulell.
	        *
	        */
	        
	       public void amagarBlanques() {
	        	for (int i = 1;i<taulell.length;++i) {
	        		for (int j = 1;j<taulell[0].length;j++) {
	        			if (!taulell[i][j].esNegra()) ((CellaBlanca)taulell[i][j]).setBlanca(-1);
	        		}
	        	}
	        }
	        
	        /**
	         *Aquesta funcio comprova que dos taulells siguin identics.
	         * @param c1 primer taulell a comparar
	         * @param c2 segon taulell a comparar
	         * @return boolean, cert si son iguals
	         */
	         
	        public boolean taulellsIdentics(Cella[][] c1, Cella[][] c2) 
	        {
	    	   int midaF = c1.length;
	    	   int midaC = c1[0].length;
	    	   for(int i = 1; i < midaF; i++) {
	    		   for(int j = 1; j < midaC; j++) {
	    			   if(!c1[i][j].esNegra())
	    				   if(((CellaBlanca)c1[i][j]).getValorBlanca() != ((CellaBlanca)c2[i][j]).getValorBlanca()) return false;
	    		   }
	    	   }
	    	   return true;
	    	   
	        }
	       
	       /**
	        * Aquesta funcio invoca la funcio resetValors de cellaBlanca, la qual retorna el valor null a les caselles balnques.
	        */
	       
	        public void reset() 
	        {
	    	   
	    	   int midaF = taulell.length;
	    	   int midaC = taulell[0].length;
	    	   
	    	   for(int i = 1; i < midaF; i++) {
	    		   for(int j = 1; j < midaC; j++) {
	    			   if(!taulell[i][j].esNegra()) ((CellaBlanca)taulell[i][j]).resetValors();
	    		   }
	    	   }
	    	   
	        }

		
}