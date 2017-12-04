package com.BioProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.biojava.nbio.sequencing.io.fastq.Fastq;
import org.biojava.nbio.sequencing.io.fastq.FastqReader;
import org.biojava.nbio.sequencing.io.fastq.SangerFastqReader;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Hash {
	int base;
	Set<String> kmers;
	int k;
	Map<String,Long> map1;
	Map<Long,Long> map2;
	public Hash(int k,Set<String> kmers){
		this.k=k;
		this.kmers=kmers;
		boolean isInjective=false;
		map1=new HashMap<String,Long>();
		map2=new HashMap<Long,Long>();
		int P=get_prime(k-1,kmers.size());
		//int length=kmers.size();
		while(!isInjective){
			isInjective=true;
			Set<Long> set=new TreeSet<Long>();
			this.base=(int)(Math.random()*(P-1));
			for(String key:kmers){
				//String key=kmers.get(i);
				long value=karpRabin(key,P);
				if(set.contains(value)){
					isInjective=false;
					map1.clear();
					break;
				}
				else{
					set.add(value);
					map1.put(key, value);
				}
			}
			
		}
		long count=0;
		for(String key:map1.keySet()){
			map2.put(map1.get(key), count);
			count++;
		}
	}
	public long getIndex(String kmer){
		return map2.get(map1.get(kmer));
	}
	public Map<String,Long> getMap1(){
		return map1;
	}
	public long getKP(String kmer){
		return map1.get(kmer);
	}
    public static void main(String[] args) throws FileNotFoundException,
            IOException {

        FileInputStream inputFastq = new FileInputStream("read_1.fq");
        FastqReader qReader = new SangerFastqReader();

        int k = 31;
        Set<String> nodes = new HashSet<String>();
        for (Fastq fastq : qReader.read(inputFastq)) {
            String read = fastq.getSequence();
//            System.out.println(read);
            for (int i = 0; i <= read.length()-k; i++) {
                nodes.add(read.substring(i, i + k - 1));
                nodes.add(read.substring(i + 1, i + k));
            }
        }
        boolean isInjective=false;
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		int P=get_prime(k-1,nodes.size());
		//int length=kmers.size();
		while(!isInjective){
			isInjective=true;
			Set<Long> set=new TreeSet<Long>();
			int base=(int)(Math.random()*(P-1));
			System.out.println(P);
			for(String key:nodes){
				//String key=kmers.get(i);
				long value=karp(key,P,base);
				System.out.println(key+" "+value);
				if(set.contains(value)){
					isInjective=false;
					hmap.clear();
					System.out.println(key+" "+value);
					System.out.println(set.size()+"Not OK");
					break;
				}
				else{
					set.add(value);
					hmap.put(key, (int)value);
				}
			}
			
		}
		System.out.println("OK");
		HashMap<Integer, Integer> hmap2 = new HashMap<Integer, Integer>();
		int count=0;
		for(String key:hmap.keySet()){
			 hmap2.put(hmap.get(key), count);
			count++;
		}
		for(String key:hmap.keySet()){
			 System.out.println(key+":"+hmap2.get(hmap.get(key)));
		}
        /*String alphabet = "ACGT";
        int d = alphabet.length();
        int q = get_prime(30, nodes.size());
        System.out.println(q);

        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        int amx=0;
        for (String node: nodes) {
        	int kk=karp_rabin(node, q, d, alphabet);
        	amx=Math.max(amx, kk);
            hmap.put(node, karp_rabin(node, q, d, alphabet));
        }
        Set<Integer> set=new TreeSet<Integer>();
        for(String node:hmap.keySet()){
        	if(set.contains(hmap.get(node))){
        		System.out.println(hmap.get(node));
        	}
        	else{
        		set.add(hmap.get(node));
        	}
        }
        HashMap<String, Integer> hmap2 = new HashMap<String, Integer>();
        int value = 0;
        for (String node: nodes) {
            hmap2.put(node, value);
            value += 1;
        }*/

        //System.out.println(hmap.size());
        //System.out.println(amx);
    }

    public static int karp_rabin(String test, int q, int d, String alphabet){
        int M = test.length();
        int res = 0;
        for (int i = 0; i < M; i++) {
            res = (d*res + alphabet.indexOf(test.charAt(i))) % q;
        }
        return res;
    }
    private long karpRabin(String key,int P){
    	int m=key.length();
    	long h=0;
    	for(int j=0;j<m;j++){
    		 h = (base * h + key.charAt(j)) % P;
    	}
    	return h;
    }
    private static long karp(String key,int P, int base){
    	int m=key.length();
    	long h=0;
    	for(int j=0;j<m;j++){
    		 h = (base * h + key.charAt(j)) % P;
    	}
    	return h;
    }
    public static boolean isPrime(int n) {
        if (n%2==0) {
           return false;
        }
        int i = 3;
        while(i*i <= n) {
            if (n%i == 0) {
                return false;
            }
            i += 2;
        }
        return true;
    }

    public static int firstLargerPrime(int R) {
        int i = R + 1;
        while(!isPrime(i)) {
            i += 1;
        }
        return i;
    }

    public static int get_prime(int k, int length) {
        int R = Math.max(4, k*length*length);
        return firstLargerPrime(R);
    }
    
}
