package com.BioProject;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	deBruijnGraph graph=new deBruijnGraph();
    	System.out.println(graph.query("GCAAAGGTATGAACCAGAGGCGAGAGCAGT"));
    	System.out.println(graph.query("CGCCTGCCGGAAGCCTGGCAGTAACCGTTC"));
    }
}
