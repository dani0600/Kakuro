package domini.model;

public class Jugador {
	
	//atributs
	protected int ID;	
	private String Password;
	
	/**
	    * Constructora de Jugador
	    *
	    */
	public Jugador() {}
	
	/**
	    * Constructora de Jugador amb identificador
	    *
	    * @param ID identificador del jugador
	    * @param Password contrasenya del jugador 
	    */
	
	public Jugador(int ID, String Password){
	    this.ID = ID;
	    this.Password = Password;
	}
	
	/**
	    * Es retorna l'identificador del jugador
	    *
	    * @return <code>ID</code> identificador del jugador
	    */
	public int getID() {
		return ID;
	}
	
	/**
	    * Es canvia l'identificador del jugador per x
	    *
	    * @param x nou identificador
	    * @return <code>true</code>
	    */
	public boolean setID(int x) {
		ID = x;
	    return true;
	}
	
	/**
	    * Es retorna el password del jugador
	    *
	    * @return <code>ID</code> password del jugador
	    */
	public String getPassword() {
		return Password;
	}
	
	
	/**
	    * Es canvia el password del jugador per p
	    *
	    * @param p nou password
	    * @return <code>true</code>
	    */
	public boolean setPassword(String p) {
		Password = p;
	    return true;
	}
	
}

