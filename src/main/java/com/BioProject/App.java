package com.BioProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	deBruijnGraph graph=new deBruijnGraph();
    	//System.out.println(graph.query("GCAAAGGTATGAACCAGAGGCGAGAGCAGT"));
    	//System.out.println(graph.query("CGCCTGCCGGAAGCCTGGCAGTAACCGTTC"));
    	/*boolean flag=false;
    	while(!flag) {
    		String[] ss=generate();
    		if(graph.query(ss[0])&&graph.query(ss[1])) {
    			flag=!graph.queryEdge(ss[0], ss[1]);
        		if(flag)System.out.println(ss[0]+" "+ss[1]);
    		}
    		
        	
    	}*/
    
    	String u="GTCTGAACTCCA";
    	//while(!graph.query(u))u=g();
    	String v="TCTGAACTCCAG";
    	//while(!graph.query(v))v=g();

    	System.out.println(graph.queryEdge(u, v));
    	graph.removeEdge(u, v);
    	System.out.println(graph.queryEdge(u, v));
    	graph.addEdge(u, v);
    	System.out.println(graph.queryEdge(u, v));
    	
    }
    static String[] generate() {
    	char[] s= {'A','C','G','T'};
    	StringBuilder sb=new StringBuilder();
    	Random rand=new Random();
    	for(int i=0;i<29;i++) {
    		sb.append(s[rand.nextInt(4)]);
    	}
    	String base=sb.toString();
    	String[] res=new String[2];
    	res[0]=s[rand.nextInt(4)]+base;
    	res[1]=base+s[rand.nextInt(4)];
    	return res;
    }
    static String g() {
    	char[] s= {'A','C','G','T'};
    	StringBuilder sb=new StringBuilder();
    	Random rand=new Random();
    	for(int i=0;i<30;i++) {
    		sb.append(s[rand.nextInt(4)]);
    	}
    	return sb.toString();
    }
}
