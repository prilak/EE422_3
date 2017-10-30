
package assignment3;

import java.util.*;
import java.lang.*;

class Vertex {
	private String name;
	//private int weight;
	//private Map<String, Integer> edges;
	private ArrayList<Vertex> n;
	private HashMap<String, Integer> weights;
	public Vertex(String name){
		//this.weight = 0;
		this.name = name;
		this.n = new ArrayList<Vertex>();
		this.weights = new HashMap<String, Integer>();
		//this.edges  = new HashMap<String, Integer>();
		//this.edges.put(name, 1);

	}

	//gets name String
	public String getName(){
		return name;
	}

	//gets n ArrayList
	public ArrayList<Vertex> getList(){
		return n;
	}

	//returns -1 if no name match
	//returns int weight if String name is found
	public int getWeight(String name){
		if (!(weights.containsKey(name))){
			return -1;
		}
		Integer num = weights.get(name);
		return num.intValue();
	}

	// gets Vertex from ArrrayList
	// returns Vertex if it contains the name
	// returns null if it does not contain name
	public Vertex get(String name){
		if (n.contains(new Vertex(name))){
			int i = n.indexOf(new Vertex(name));
			return n.get(i);
		} else {
			return null;
		}

	}
	
	public boolean contains(Vertex v){
		ListIterator<Vertex> i = n.listIterator();
		while(i.hasNext()){
			Vertex a = i.next();
			//System.out.print(a.getName() + " ");
			if(a.equals(v)){
				return true;
			}
		}
		//System.out.println("");
		return false;
	}

	//adds a Vertex to the next
	//returns true if name was added
	//returns false if name exists already
	public boolean add(String name){
		if (contains(new Vertex(name))){
			//System.out.println("should println");
			return false;
			
		}
		Vertex aV = new Vertex(name);
		//System.out.println(n.size());
		n.add(aV);
    weights.put(name, 1);

		return true;
	}

	// //if contains a Vertex with  String name, return Vertex
	// //else return null
	// public Vertex contains(String name){
	// 	if(next.indexOf)
	// }

	//increment weight
	// gets Integer value from String key
	//increment Integer
	public void increment(String connection){
		//Vertex v = weights.get(connection)
		Integer weight = this.weights.get(connection);
		weight = new Integer(1 + weight.intValue());
		weights.put(connection, weight);
		//this.weight++;
	}
	
	//@Override
	public boolean equals(Object a){
		//System.out.println(((Vertex)a).getName()==this.getName());
		return (((Vertex)a).getName().equals(this.getName()));

	}
	@Override
	public String toString(){
		String s = "[";
		//System.out.print("[");
		ListIterator i = n.listIterator();
		while(i.hasNext()){
			s += " " + ((Vertex)i.next()).getName() + ",";
		}
		return s += " ]";
	}

	
}