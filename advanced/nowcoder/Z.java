//Initialization procedure
/*
1. static variable and code of the parent class
2. static variable and code of the current class
3. regular varaible  and code of the parent class, call parent constructor.
4. regular variable and code of the current class, call constructor.
*/
class X {
	Y y = new Y();
	public X() {
		System.out.print("X");
	}
}

class Y {
	public Y() {
		System.out.print("Y");
	}
}

public class Z extends X {
	Y y = new Y();
	public Z() {
		System.out.print("Z");
	}
	public static void main(String[] args) {
		new Z();
	}
}