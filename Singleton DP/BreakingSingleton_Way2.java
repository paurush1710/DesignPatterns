import java.lang.reflect.Constructor;

public class Main {

	public static void main(String[] args) throws Exception{
	
//		1. Reflection API to break singleton pattern
//		Solution: 1-> if object is there ==> throw Exception from inside constructor
//		2. Use Enum
		
		Box b = Box.INSTANCE; 
		System.out.println(b.hashCode());
		b.test();   // This method will be printed and will give output

		// This will give Runtime Error as we are trying to create second constructor
		Constructor<Box> constructor = Box.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Box b2 = constructor.newInstance();
		System.out.println(b2.hashCode());
	}

}

// Way to stop breaking Singleton pattern usinf Enum

public enum Box {
	
	INSTANCE;

	public void test() {
		System.out.println("Test method");
	}
	
}
