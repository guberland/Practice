public class IntSearch {
  public static void main (String args[]){
    int[] test={0,1,1,2,3,5,5,5,5,6,8,9,9,9};
    findMatches(test,5);
  }
/**
* Returns a Matches object that represents where and how often the query
* argument appeared in the values array.
*
* @param values A pre-sorted array of integers, possibly containing
* duplicates.
* @param query The integer to search for.
*/
  
  public static Matches findMatches(int[] values, int query) {
      
      // Check edge cases
      if(values == null || values.length < 1) return new Matches(-1, 0);
      
      // Check if query is in the range of the sorted array
      if(query < values[0] || query > values[values.length - 1]) return new Matches(-1, 0);
      
      // Binary Search
      int low = 0;
      int high = values.length - 1;
      
      while(low <= high){
          int mid = low + (high - low)/2;
          if(query < values[mid]){
              high = mid - 1;
          }else if(query > values[mid]){
              low = mid + 1;
          }else{
		// if we found a match, use refactored helper method to initialize a Matches object
		Matches match = createMatches(values, mid);
              return match;
          }
      }
      
      return new Matches(-1, 0);
  }
  
  /**
   * A helper function to create Matches object
   */
   
  private static Matches createMatches(int[] values, int foundIndex){
      
      int numOfMatches = 1;
      int left = foundIndex - 1;
      int right = foundIndex + 1;

	int firstMatchIndex = foundIndex;
	int target = values[foundIndex];
      
      while(left >= 0 && values[left] == target){            
          numOfMatches++;
	    firstMatchIndex--;
	    left--;
      }
      
      while(right <= values.length - 1 && values[right] == target){          
          numOfMatches++;
	    right++;
      }
        System.out.println(firstMatchIndex);
    System.out.println(numOfMatches);
      return new Matches(firstMatchIndex, numOfMatches);
  }
}
  
  
  
  
  
  
  
  
  
  
  
  /*public static Matches findMatches(int[] values, int query) {
	    int counter=0;
	  	int low=0;
	  	int match=-1;
	  	
	  	if(values==null||values.length==0||query<values[0]||query>values[values.length-1])
	  		{
	  		System.out.println(counter);
	  		System.out.println(match);
	  		return new Matches(-1,0);
	  		}
	  		
	  	
	  	int high=values.length-1;
		
	while(low<=high){
		int mid=(low+high)/2;
		if(values[mid]>=query){
		high=mid-1;
			}
		else low=mid+1;		
		}
	for(int i=low;i<=values.length-1;i++){
		
		if(values[i]==query){
			match=low;			
		while(values[i]==query&&i<values.length-1){
			counter++;
			i++;
				}		
			}
		if(values[i]==query&&i==values.length-1){
			counter++;
			}
		}
	System.out.print(counter);
	return new Matches(match,counter);
	}	
}
  */