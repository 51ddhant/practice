import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a number: ");
		int a = sc.nextInt();
		int b = (int) Math.sqrt(a);
		boolean check = true;
		for(int i = 2; i<=b; i++) {
			if(a%i == 0) {
				check = false;
			}
		}
		if(check == true) {
			System.out.println(a + " is a prime number.");
		} else {
			System.out.println(a + " is not a prime number.");
		}
		sc.close();
		
	}
}
