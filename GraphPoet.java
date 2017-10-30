
package assignment3;

import java.io.BufferedReader;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphPoet {
    private Vertex root;    

    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */

    public GraphPoet(File corpus) throws IOException {
        root = new Vertex("root");
        /* Read in the File and place into graph here */
        //this.words = new TreeSet();
        Scanner fileIn = new Scanner(corpus);
        while(fileIn.hasNextLine()){
            // process line
            String line = fileIn.nextLine();
            Scanner lineParser = new Scanner(line);

            //a: add to hashmap
            //b: add a to hashmap.b.arraylist
            //c: add a to hashmap.c.arraylist.b.arraylist
            String a = null;
            String b = null;
            String c = null;
            while(lineParser.hasNext()){
                // parse each word
                //delete punctuation except for '
                //convert to lowercase
                a = lineParser.next();
                a = format(a);
                // check if there is a present Vertex, 
                // if not->add one
                //System.out.println(a);
                boolean z = root.add(a);
                    //get word Vertex and incriment count

                
                //System.out.println(root.next.get(0));

                
                if(b!=null){
                    Vertex bV = root.get(b);
                    if(!(bV.add(a))){// increment weight
                        bV.increment(a);
                    }
                }
                if(c!=null){
                    Vertex cV = root.get(c);
                    Vertex bV = cV.get(b);
                    if(!(bV.add(a))){
                        bV.increment(a);
                    }
                }
                //Swaps a->b b->c
                String hold = b;
                b = a;
                c = hold;


            }
        }

        //-----------------------------------
        // testing code
        //-----------------------------------
        // System.out.println(root);
        // //System.out.println(root.next.contains("to"));
        // System.out.println(root.equals(new Vertex("root")));
        // System.out.println(root.getList().get(0).getList().get(1));
        // System.out.println(root.getList().get(0).getWeight("strange"));

        // System.out.println(root.getList().get(0).getList().get(0).getWeight("strange"));
        // // Vertex y = root.n.get(0); 
        // // System.out.println(y.equals(new Vertex("to")));
        // // System.out.println(root.n.get(1));

    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {
        Scanner in = new Scanner(input);
        String poem = "";
        while(in.hasNextLine()){
            String line = in.nextLine();
            Scanner lineParser = new Scanner(line);
            String a = null;
            String b = null;
            

            if(lineParser.hasNext()){
                a = lineParser.next();
                //a = GraphPoet.format(a);
                poem += a;
            }

            while(lineParser.hasNext()){
                b = lineParser.next();
                //b = GraphPoet.format(b);
                String x = findLink(GraphPoet.format(a), GraphPoet.format(b));
                if(x.equals("")){//no x was found
                    poem += " " + b;
                } else {
                    poem += " " + x + " " + b;
                }
                
                a = b;
            }
        }
        /* Read in input and use graph to complete poem */

        // String poem = "";
        return poem;
    }

    //checks if there exists a link between a->x->b for some x
    //returns true if x exists
    //returns false if x does not exist
    private String findLink(String a, String b){
        if(!root.contains(new Vertex(a))){
            return "";
        }
        //get element a
        int weight = 0;
        String insert = "";
        ArrayList<Vertex> l = root.getList();
        int indexA = l.indexOf(new Vertex(a));
        ListIterator<Vertex> iA = root.getList().get(indexA).getList().listIterator();
        while(iA.hasNext()){
            Vertex x = iA.next();
            if(x.getWeight(b)>weight){
                weight = x.getWeight(b);
                insert = x.getName();
            }
            
        }
        return insert;    
    }

    // parse each word
    //delete punctuation except for '
    //convert to lowercase
    private static String format(String a){
        if(!Character.isLetter(a.charAt(a.length()-1))){// if word ends in punctuation
            a = a.substring(0,a.length()-1);// splices string before the punctuation
        }

        //convert a to lowercase
        return a.toLowerCase();
    }
}
