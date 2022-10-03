package ex1;

public class exc1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 12345, reversed = 0;
	    
	    System.out.println("Given Number: " + num);

	    // run loop until num becomes 0
	    while(num != 0) {
	    
	      // get last digit from num
	      int digit = num % 10;
	      reversed = reversed * 10 + digit;

	      // remove the last digit from num
	      num /= 10;
	    }

	    System.out.println("Reverse Number: " + reversed);
	  
	}

}
