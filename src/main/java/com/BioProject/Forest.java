package com.BioProject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public class Forest {
	
	
	BitSet bitarray;
	int n;
	Map<Integer, String> roots;
	
	public Forest(int size){
		bitarray=new BitSet(4*size);
		n=size;
	}
	int nodeIndex(int i){
		return 4*i;
	}
	void setNode(int i, boolean IN, Letter l){
		int index=nodeIndex(i);
		bitarray.set(index, false);
		bitarray.set(index+1,IN);
		bitarray.set(index+2,l.bits.get(0));
		bitarray.set(index+3,l.bits.get(1));
	}
	void setLetter(int i, Letter l){
		int index=nodeIndex(i);
		bitarray.set(index+2,l.bits.get(0));
		bitarray.set(index+3,l.bits.get(1));
	}
	Letter getLetter(int i){
		int index=nodeIndex(i);
		Letter l=new Letter(bitarray.get(index+2),bitarray.get(index+3));
		return l;
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
		bitarray.set(nodeIndex(i),false);
	}
	boolean isStored(int i){
		return bitarray.get(nodeIndex(i));
	}
	boolean parent_in_IN(int i){
		return bitarray.get(nodeIndex(i)+1);
	}
	void set_parent_in_IN(int i, boolean in){
		bitarray.set(nodeIndex(i)+1,in);
	}
	String getNext(int i,String kmer){
		int index=nodeIndex(i);
		Letter l=new Letter(bitarray.get(index+2),bitarray.get(index+3));
		if(bitarray.get(index+1)){
			return pushOnFront(kmer,l);
		}
		else{
			return pushOnBack(kmer,l);
		}
	}
	public String pushOnFront(String kmer,Letter l){
		return kmer.substring(1)+l.getChar();
	}
	public String pushOnBack(String kmer,Letter l){
		return l.getChar()+kmer.substring(0, kmer.length()-1);
	}
	
}
