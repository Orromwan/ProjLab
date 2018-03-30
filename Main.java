package projlab;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Válassz teszt szekvenciát 1-10ig a szám megadásával:");
		/*
		Scanner in = new Scanner(System.in);

		int i = in.nextInt();
		String s = in.next();
		*/
		String inputStr = System.console().readLine();
		int input = Integer.parseInt(inputStr);
		
		switch(input) {
		case 1: seq01();
		case 2: seq02();
		case 3: seq03();
		case 4: seq04();
		case 5: seq05();
		case 6: seq06();
		case 7: seq07();
		case 8: seq08();
		case 9: seq09();
		case 10: seq10();		
		defaut: System.out.println("Rossz érték!");
			
		
		}
	}
	
	// Munkás üres mezõre lép
	public static void seq01() {		
		
	}
	public static void seq02() {
		
	}
	public static void seq03() {
		
	}
	public static void seq04() {
		
	}
	public static void seq05() {
		
	}
	public static void seq06() {
		
	}
	public static void seq07() {
		
	}
	public static void seq08() {
		
	}
	public static void seq09() {
		
	}
	public static void seq10() {
		
	}
	
	
}
