import java.util.Stack;
import java.util.Scanner;
class SubSet{
  int set[];
  int sum;
  
  Stack<Integer> solutionSet;
  boolean hasSolution;
  
  SubSet(int set[], int sum){
      this.set = set;
      this.sum = sum;
      this.solutionSet = new Stack<>();
      hasSolution = false;
  }
  
  public void solve(int s, int idx){   
    //return false if s value exceed sum
    if(s>sum)
        return;
 
    //check if stack has the right subsets of numbers
    if(s==sum){
        hasSolution = true;
 
        //display stack contents
        displaySolutionSet();
 
        //Though found a solution but deliberately returning to find more
        return;
    }
        
    for(int i=idx; i<set.length; i++){
        //Adding element to the stack
        solutionSet.push(set[i]);
 
        //add set[i] to the 's' and recusively start from next number
        solve(s+set[i],i+1);
 
        //Removing element from stack i.e Backtracking
        solutionSet.pop();
    }
  }
  
  //Function to display stack content
  private void displaySolutionSet(){
    for (Integer item: solutionSet){
      System.out.print(item+" ");
    }
        System.out.println();
  }
   
}
 
public class backtracking 
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
       //int set[] = {5,10,12,13,15,18};
       int count = sc.nextInt();
       
       int set[] = new int[5];
       for(int i=0;i<count;i++){
          set[i] = sc.nextInt();
       }
       int size = 30;
    
       SubSet ss = new SubSet(set, size);
       
       ss.solve(0,0);
	    
       if(ss.hasSolution == false)
	  System.out.print("No Solution");
    }
}