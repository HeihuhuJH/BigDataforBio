package com.BioProject;

import java.util.BitSet;

public class Letter {
	BitSet bits;
	void setBits(char c){
		if(c=='A'){
			bits.set(0,0);
			bits.set(1,0);
		}
		else if(c=='C'){
			bits.set(0,0);
			bits.set(1,1);
		}
		else if(c=='G'){
			bits.set(0,1);
			bits.set(1,0);
		}
		else if(c=='T'){
			bits.set(0,1);
			bits.set(1,1);
		}
	}
	Letter(boolean b1,boolean b2){
		bits.set(0,b1);
		bits.set(0,b2);
	}
	public Letter(int i){
		if(i==0){
			bits.set(0,0);
			bits.set(1,0);
		}
		else if(i==1){
			bits.set(0,0);
			bits.set(1,1);
		}
		else if(i==2){
			bits.set(0,1);
			bits.set(1,0);
		}
		else if(i==3){
			bits.set(0,1);
			bits.set(1,1);
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
