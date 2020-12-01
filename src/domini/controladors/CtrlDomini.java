package domini.controladors;

import domini.model.*;

public class CtrlDomini {
	
	private static final CtrlDomini ctrlDomini = new CtrlDomini();
	
	private CtrlDomini() {
		
	}
	
	public static CtrlDomini getInstance() 
	{
		return ctrlDomini;	
	}
	
	public CtrlTablero getCtrlTablero() 
	{
		return CtrlTablero.getInstance();
	}
	
	
	
	
}
