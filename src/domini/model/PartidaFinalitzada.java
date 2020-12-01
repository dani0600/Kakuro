package domini.model;

public class PartidaFinalitzada extends Partida{

	private boolean end;
	
	/**
	    * Constructora de PartidaFinalitzada
	    * 	   
	    * 
	    * */
	public PartidaFinalitzada() {}
	
	/**
	    * Constructora de PartidaFinalitzada amb jugador, dificultat i tots els temps
	    * 	   
	    * @param ID identificador del Jugador
	    * @param dificultat nivell de dificultat de la Partida
	    * @param temps temps total de la Partida
	    * @param tIni temps inicial que comenca la Partida
	    * @param end boolean per saber si la Partida esta finalitzada. 
	    * */
	
	public PartidaFinalitzada(int ID, int dificultat, double temps, long tIni, boolean end)
	{
		super(ID, dificultat, temps, tIni);
		this.end = true;
	}
	
	
	/**
	    * Es retorna si la Partida esta finalitzada
	    * 	   
	    * @return <code>end</code>
	    * */
	public boolean isEnded() 
	{
		return end;
	}
	
	/**
	    * S'acaba la Partida
	    * 	   
	    * @param end boolea de si s'ha acabat la partida
	    * */
	public void setEnd(boolean end)
	{
		this.end = end;
	}
	
	/**
	    * Es canvia el temps final de la Partida
	    * 	   
	    * 
	    * */
	public void setTempsFinal() 
	{
		long tFi = System.currentTimeMillis();
		double tTotal = (double) ((tFi - super.getTempsInicial())/1000);
		tTotal += super.getTemps();
		super.setTemps(tTotal);
		
	}
	
	
	
	
}
