import java.util.*;

public class scratch {

static void swap(String str1, String str2){

        String temp = str1;

        str1 = str2;

        str2 = temp;

    }


    static void setNewValue(String str1){

        str1 = new String("Bittiger");

    }


	public static void main (String args[]) {

    
String str1 = "Bit";

        String str2 = "Tigger";

        swap(str1, str2);

        System.out.println("str1:  " + str1);        

        System.out.println("str2:  " + str2);            

        setNewValue(str1);
        System.out.println("str1:  " + str1);    
 
	
	}
}