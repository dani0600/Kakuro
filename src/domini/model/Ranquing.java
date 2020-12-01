package domini.model;

import java.util.*;

public class Ranquing{
	
	//atributs
	SortedMap<Integer,Double>easy;
	
	SortedMap<Integer,Double>mid;
	
	SortedMap<Integer,Double>hard;
	
	/**
	    * Constructora de Ranquing
	    * 	   
	    * 
	    * */
	public Ranquing() {}
	
	/**
	    * Constructora de Ranquing amb tots els mapes de les dificultats
	    * 	   
	    * @param easy Ranquing nivell facil
	    * @param mid Ranquing nivell mig
	    * @param hard Ranquing nivell dificil
	    * */
	public Ranquing(SortedMap<Integer,Double> easy,SortedMap<Integer,Double> mid, SortedMap<Integer,Double> hard)
	{
	    this.easy = easy;
		this.mid = mid;
		this.hard = hard;
	}
	
	/*---Consultoras---*/
	
	public SortedMap<Integer,Double> getEasy(){
		return this.easy;
	}
	public SortedMap<Integer,Double> getMid(){
		return this.mid;
	}
	public SortedMap<Integer,Double> getHard(){
		return this.hard;
	}

	
	/*---Modificadoras---*/
	
	public void setEasy(SortedMap<Integer,Double> nm) {
		easy =  nm;
	}
	
	public void setMid(SortedMap<Integer,Double> nm) {
		mid =  nm;
	}
	
	public void setHard(SortedMap<Integer,Double> nm) {
		hard =  nm;
	}
}