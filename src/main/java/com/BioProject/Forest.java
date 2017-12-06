package com.BioProject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Forest {
	
	
	BitSet bitarray;
	int n;
	public Map<Integer, String> roots;
	public char[] parents;
	public boolean[] inorout;
	public boolean[] rooted;
	public Forest(int size){
		bitarray=new BitSet(4*size);
		n=size;
		roots=new HashMap<Integer,String>(n);
		parents=new char[n];
		inorout=new boolean[n];
		rooted=new boolean[n];
	}
	int nodeIndex(int i){
		return 4*i;
	}
	void setNode(int i, boolean IN, char l){
		//int index=nodeIndex(i);
		parents[i]=l;
		inorout[i]=IN;
		rooted[i]=false;
		/*bitarray.set(index, false);
		bitarray.set(index+1,IN);
		bitarray.set(index+2,l.bits.get(0));
		bitarray.set(index+3,l.bits.get(1));*/
	}
	void setLetter(int i, char l){
		//int index=nodeIndex(i);
		parents[i]=l;
		//bitarray.set(index+2,l.bits.get(0));
		//bitarray.set(index+3,l.bits.get(1));
	}
	char getLetter(int i){
		//int index=nodeIndex(i);
		//Letter l=new Letter(bitarray.get(index+2),bitarray.get(index+3));
		return parents[i];
	}
	List<Boolean> getNodeData(int i){
		List<Boolean> data=new ArrayList<Boolean>();
		int index=nodeIndex(i);
		data.add(bitarray.get(index));
		data.add(bitarray.get(index+1));
		data.add(bitarray.get(index+2));
		data.add(bitarray.get(index+3));
		return data;
	}
	void storeNode(int i,String kmer){
		roots.put(i, kmer);
		rooted[i]=true;
		//bitarray.set(nodeIndex(i),false);
	}
	boolean isStored(int i){
		return rooted[i];
	}
	boolean parent_in_IN(int i){
		//return bitarray.get(nodeIndex(i)+1);
		return inorout[i];
	}
	void set_parent_in_IN(int i, boolean in){
		//bitarray.set(nodeIndex(i)+1,in);
		inorout[i]=in;
	}
	String getNext(int i,String kmer){
		int index=nodeIndex(i);
		//Letter l=new Letter(bitarray.get(index+2),bitarray.get(index+3));
		char l=parents[i];
		//System.out.println(l.getChar());
		if(inorout[i]) {
			return pushOnFront(kmer,l);
		}
		
		else{
			return pushOnBack(kmer,l);
		}
	}
	void unstoreNode(int i){
		this.roots.remove(i);
		rooted[i]=false;
		this.bitarray.set(nodeIndex(i),false);
	}
	public String pushOnFront(String kmer,char l){
		
		return l+kmer.substring(0, kmer.length()-1);
	}
	public String pushOnBack(String kmer,char l){
		return kmer.substring(1)+l;
	}
	
}
