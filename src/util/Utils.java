package util;

import java.util.List;

public class Utils {

	public static <T> T getMostOccoringElement(List<T> list) {
	    int size = list.size();
	    if(size == 0)
	        return null;
	     
	    int count = 0;
	    int maxCount = 0;
	    T element = list.get(0);
	    T mostOccuringElement = element;
	     
	    for(int index = 0; index<size; index++) {
	        if(list.get(index).equals(element)) {
	            count++;
	            if(count > maxCount) {
	                maxCount = count;
	                mostOccuringElement = element;
	            }
	        } else {
	            count = 1;
	        }
	        element = list.get(index);
	    }
	    return mostOccuringElement;
	}
}
