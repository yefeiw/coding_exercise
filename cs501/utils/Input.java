package utils;
import java.util.*;
//package utils;
public class Input {
	//member functions to generate input.
	public ArrayList<Integer> generateRandomArrayList() {
		Random rand = new Random();
		int maxSize = 10000;
		int size = rand.nextInt(maxSize+1);
		ArrayList<Integer> ret = new ArrayList<Integer>();//returning arraylist of integer.
		for (int i = 0; i < size; i++) {
			ret.add(rand.nextInt());
		}
		return ret;
	}
}