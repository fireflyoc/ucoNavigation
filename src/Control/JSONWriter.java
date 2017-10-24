package Control;

import Model.Node;
import java.util.LinkedList;

/**
 * Created by Decker on 10/12/2017. 
 *  
 */
public class JSONWriter {

   public String convertListToJsonArray(LinkedList<Node> list){
     String output = "{\"userPath\":[";
       
       for(Node n: list){
           output+="{\"id\":\""+n.getID()+"\",";
           output+="\"lat\":\""+n.getLat()+"\",";
           output+="\"lon\":\""+n.getLon()+"\"},";
       } //end for loop
       output = output.substring(0, output.length()-1);
       output +="]}";
       return output;
   } //end 

} //end JSONWriter
