package domini.controladors;

import domini.model.Tablero;
import domini.model.Cella;

import java.io.*;

public class CtrlTablero {
	
	private static final CtrlTablero ctrlTablero = new CtrlTablero();
	//private final CtrlPresentacio ctrlPresentacio = ctrlPrese
	private Tablero tab = new Tablero();
	
	public CtrlTablero() {
		
	}
	
	public static CtrlTablero getInstance() 
	{
		return ctrlTablero;	
	}
	
	public void intTablero(BufferedReader br) 
	{
		try {
			tab.introduirTab(br);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/*public void imprimeixTablero()
	{
		CtrlPresentacio.getInstance().print();
	}*/
	
	public Cella[][] getTaulell() 
	{
		return tab.getTaulell();
	}
	
	
	
	
	
	
	
	
	
}
