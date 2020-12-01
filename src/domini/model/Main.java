package domini.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    
	public static void main(String[] args){
			String input;
			System.out.print("Benvingut a l'aplicació de Kakuros!!!\n");
			
			try {
	             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
	             boolean sortir = false;
	             while (!sortir) {
	            	 System.out.println("Disposem de les seguents funcionalitats: ");
				
	            	 System.out.print("\n");
	            	 System.out.println("1 - Generar un Kakuro Aleatori");
	            	 System.out.println("2 - Resoldre un Kakuro que s'introdueixi per teclat");
	            	 System.out.println("3 - Sortir");
			
	            	 System.out.println(" A continuació introdueixi per teclat una opció (1, 2 o 3)");
				 
	            	 input = br.readLine();
	            	 int num = Integer.valueOf(input);
	            	 	            	 
	            	 if (num == 1) {
	            		 Tablero T = new Tablero();
	            		 long startTime = System.nanoTime();
	            		 T.creaKakuroByNegres();
	            		 long endTime = System.nanoTime();
	            		 T.amagarBlanques();
	            		 T.print();
	            		 System.out.print("\n");
	            		 System.out.print("Temps de generació del Kakuro (milis): ");
	            		 System.out.print((endTime-startTime)/1000000);					
	            		 System.out.print("\n");
					
	            	 }
				
	            	 else if (num == 2) {
	            		Tablero T = new Tablero();
	            		T.introduirTab(br);
						Cella[][] c = T.getTaulell();
						
						Solver s = new Solver();					
						int count = 0;
						long startTime = System.nanoTime();
						boolean sol = s.solve(c,1,1,1);
						long endTime = System.nanoTime();
						if (!sol) System.out.println("No té solució!");
						else {
							++count;
							T.print();
							Cella c1[][] = new Cella[c.length][c[0].length];
							for(int i = 0; i < c.length; i++) {
								for(int j = 0; j < c[0].length; j++) {
									if(c[i][j].esNegra()) c1[i][j] = new Negra(((Negra)c[i][j]).getValorDreta(), ((Negra)c[i][j]).getValorEsq());
									else c1[i][j] = new CellaBlanca(((CellaBlanca)c[i][j]).getValorBlanca(), i, j);
								}
							}
							System.out.print("Temps de resolució del Kakuro (milis): ");
							System.out.print(((endTime-startTime)/1000000));
							System.out.print("\n");
							System.out.print("\n");
							
							T.reset();
							c = T.getTaulell();
							startTime = System.nanoTime();
							if(s.solve(c,1,1,2)) {
								endTime = System.nanoTime();
								Cella c2[][] = T.getTaulell();
								if(!T.taulellsIdentics(c1,c2)) {
									++count;
									T.print();
									System.out.print("Temps de resolució del Kakuro (milis): ");
		    						System.out.print(((endTime-startTime)/1000000));
		    						System.out.print("\n");
								}
							}
						}
						System.out.print("Nombre de solucions del Kakuro: ");
						if(count == 2) System.out.print("2");
						else System.out.print(count);
						System.out.print("\n");
	            	 }
	            	 else if (num == 3) {
	            		 sortir = true;
	            		 br.close();
	            	 }
				
	             }		
	             System.out.println(" \n");
	             System.out.println("Tancant...\n");
		
			}
			catch(Exception e) {
	            System.out.println(e.getMessage());
			}

		}
}