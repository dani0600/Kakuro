package domini.model;

public class Partida {
	
	private int ID;
	private int dificultat;
	private double temps;
	private long tIni;
	

	/**
	    * Constructora de Partida
	    * 	   
	    * */
	public Partida(){}
	
	/**
	    * Constructora de Partida amb jugador i dificultat
	    * 	   
	    * @param ID identificador del Jugador
	    * @param dificultat nivell de dificultat de la Partida
	    * */
		
	public Partida(int ID, int dificultat)
	{
		this.ID = ID;
		this.dificultat = dificultat; 
		this.temps = 0.0;
		this.tIni = 0;
	}
	
	/**
	    * Constructora de Partida amb jugador, dificultat i tots els temps
	    * 	   
	    * @param ID identificador del Jugador
	    * @param dificultat nivell de dificultat de la Partida
	    * @param temps temps total de la Partida
	    * @param tIni temps inicial que comen�a la Partida
	    * */
	public Partida(int ID, int dificultat, double temps, long tIni)
	{
		this.ID = ID;
		this.dificultat = dificultat; 
		this.temps = temps;
		this.tIni = tIni;
	}	
		

	/**
	    * Es retorna l'identificador del Jugador
	    * 	   
	    * @return <code>ID</code> 
	    * */
	public int getID() 
	{
		return ID;
	}
	
	/**
	    * Es retorna la dificultat de la Partida
	    * 	   
	    * @return <code>dificultat</code> 
	    * */
	public int getDificultat() 
	{
		return dificultat;
	}

	/**
	    * Es retorna el temps de la Partida
	    * 	   
	    * @return <code>temps</code> 
	    * */
	public double getTemps() 
	{
		return temps;
	}
	
	/**
	    * Es retorna el temps inical de la Partida
	    * 	   
	    * @return <code>tIni</code> 
	    * */
	public double getTempsInicial() 
	{
		return tIni;
	}
	
	/**
	    * Es canvia el temps de la Partida
	    * 	   
	    * @param temps Temps total de la Partida
	    * */
	public void setTemps(double temps) {
		this.temps = temps;
	}

	/**
	    * Es comenca el temps de la Partida
	    * 	   
	    *  
	    * */
	public void startTimer()
	{
		tIni = System.currentTimeMillis();
	}
	
	/**
	    * Es pausa el temps de la Partida
	    * 	   
	    * 
	    * */
	
	public void pauseTimer()
	{
		long tPausaIni = System.currentTimeMillis();
		temps += (double) ((tPausaIni - tIni)/1000);
	}
	
}