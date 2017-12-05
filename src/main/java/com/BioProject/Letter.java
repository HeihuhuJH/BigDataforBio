package com.BioProject;

import java.util.BitSet;

public class Letter {
	BitSet bits=new BitSet(2);
	void setBits(char c){
		if(c=='A'){
			//bits.set(0,0);
			//bits.set(1,0);
		}
		else if(c=='C'){
			bits.set(1);
		
		}
		else if(c=='G'){
			bits.set(0);
			//bits.set(1,0);
		}
		else if(c=='T'){
			bits.set(0);
			bits.set(1);
		}
	}
	Letter(boolean b1,boolean b2){
		if(b1){
			bits.set(0);
		}
		if(b2){
			bits.set(1);
		}
	}
	public Letter(int i){
		if(i==0){
			
		}
		else if(i==1){
			bits.set(1);
		}
		else if(i==2){
			bits.set(0);
		}
		else if(i==3){
			bits.set(0);
			bits.set(1);
		}
	}
	char getChar(){
		if(bits.get(0)==false&&bits.get(1)==false){
			return 'A';
		}
		if(bits.get(0)==false&&bits.get(1)==true){
			return 'C';
		}
		if(bits.get(0)==true&&bits.get(1)==false){
			return 'G';
		}
		if(bits.get(0)==true&&bits.get(1)==true){
			return 'T';
		}
		return (Character) null;
	}
}
