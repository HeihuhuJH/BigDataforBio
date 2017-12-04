package com.BioProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import org.biojava.nbio.sequencing.io.fastq.Fastq;
import org.biojava.nbio.sequencing.io.fastq.FastqReader;
import org.biojava.nbio.sequencing.io.fastq.SangerFastqReader;



public class deBruijnGraph {
	int k;
	int alpha;
	Forest forest;
	boolean[][] IN;
	boolean[][] OUT;
	int n;//size of kmers
	Set<String> kmers;
	Hash hash;
	Set<String> edgemers;
	int size;
	public deBruijnGraph() throws IOException{
		ReadFastqFile();
		hash=new Hash(k,kmers);
		size=kmers.size();
		IN=new boolean[size][4];
		OUT=new boolean[size][4];
		for(String edge:edgemers){
			String front=edge.substring(0,k-1);
			String back=edge.substring(1,k);
			char first=edge.charAt(0);
			char last=edge.charAt(k-1);
			OUT[f(front)][charToIndex(last)]=true;
			IN[f(back)][charToIndex(first)]=true;
		}
		construct_forest(kmers,k*2);
	}
	int f(String kmer){
		return (int)hash.getIndex(kmer);
	}
	int charToIndex(char letter){
		if(letter=='A')return 0;
		if(letter=='C')return 1;
		if(letter=='G')return 2;
		if(letter=='T')return 3;
		return -1;
	}
	void ReadFastqFile() throws IOException{
		FileInputStream inputFastq = new FileInputStream("read_1.fq");
        FastqReader qReader = new SangerFastqReader();

 
        
        for (Fastq fastq : qReader.read(inputFastq)) {
            String read = fastq.getSequence();
//            System.out.println(read);
            for (int i = 0; i <= read.length()-k; i++) {
                kmers.add(read.substring(i, i + k - 1));
                kmers.add(read.substring(i + 1, i + k));
            }
            for (int i = 0; i < read.length()-k; i++) {
            	edgemers.add(read.substring(i, i + k ));
            	edgemers.add(read.substring(i + 1, i + k+1));
            }
        }
	}
	void move_kmer(Set<String> kmers,Set<String> visited,String kmer){
		kmers.remove(kmer);
		visited.add(kmer);
	}
	void store(String mer){
		forest.storeNode(f(mer), mer);
	}
	void construct_forest(Set<String> kmers,int a){
		alpha=a;
		Set<String> visited_mers = new TreeSet<String>();
		int[] h=new int[n];
		String[] p1=new String[n];
		String[] p2=new String[n];
		String[] p=new String[n];
		while(visited_mers.size()!=n){
			String root=kmers.iterator().next();
			store(root);
			move_kmer(kmers,visited_mers,root);
			int r=f(root);
			p1[r]=root;
			p2[r]=root;
			h[r]=0;
			Queue<String> queue=new LinkedList<String>();
			queue.add(root);
			List<String> neighbors=new ArrayList<String>();
			List<Boolean> inorout=new ArrayList<Boolean>();
			while(!queue.isEmpty()){
				String c=queue.poll();
				getNeighboors(c,neighbors,inorout);
				Letter first=new Letter(c.charAt(0));
				Letter last=new Letter(c.charAt(c.length()-1));
				for(int i=0;i<neighbors.size();i++){
					String m=neighbors.get(i);
					if(!visited_mers.contains(m)){
						queue.add(m);
						move_kmer(kmers,visited_mers,m);
						int fm=f(m);
						int fc=f(c);
						p[fm]=c;
						if(inorout.get(i)){
							forest.setNode(fm, false, last);
						}
						else{
							forest.setNode(fm, true, first);
						}
						h[fm]=h[fc]+1;
						if(h[fm]<=alpha){
							p1[fm]=p1[fc];
							p2[fm]=p2[fc];
						}
						if(h[fm]>alpha&&h[fm]<=2*alpha){
							store(p1[fc]);
							p1[fm]=p1[fc];
							p2[fm]=p1[fc];
						}
						if(h[fm]==2*alpha+1){
							h[fm]=0;
							p1[fm]=m;
							p2[fm]=p1[fc];
						}
					}
				}
			}
 		}
		kmers=visited_mers;
	}
	void getNeighboors(String c,List<String> neighbors,List<Boolean> inorout){
		neighbors.clear();
		inorout.clear();
		int fc=f(c);
		for(int i=0;i<4;i++){
			if(IN[fc][i]){
				Letter l=new Letter(i);
				String e=forest.pushOnFront(c, l);
				neighbors.add(e);
				inorout.add(true);
			}
			if(OUT[fc][i]){
				Letter l=new Letter(i);
				String e=forest.pushOnBack(c, l);
				neighbors.add(e);
				inorout.add(false);
			}
		}
	}
	public void addEdge(String u,String v){
		int fu=f(u);
		int fv=f(v);
		if(OUT[fu][charToIndex(v.charAt(0))]){
			System.out.println("Edge exisits");
			return;
		}
		OUT[fu][charToIndex(v.charAt(0))]=true;
		IN[fv][charToIndex(u.charAt(u.length()-1))]=true;
		
	}
	int getTreeHeight(String root)
	public static void main(String args[]) { 
		
	}
}
