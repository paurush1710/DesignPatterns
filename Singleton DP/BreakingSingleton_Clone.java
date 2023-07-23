import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class Main {

	public static void main(String[] args) throws Exception, CloneNotSupportedException {

//		1. Reflection API to break singleton pattern
//		Solution: 1-> if object is there ==> throw Exception from inside constructor
//		          2. Use Enum
//		
//		2. Deserialization
//		   After initial deserializtion hashcode for before/after wrting to object will be different
//		   So now we have to manage this
//		Solution : override readResolve() method
//		After writing this method 
//		      -> We can get the same object after deserialzition
//		
//		3. Cloning
//		
		
		
		Box box = Box.getBox();
		System.out.println(box.hashCode());
		
		Box b2 =(Box) box.clone();
		System.out.println(b2.hashCode());
		
	}

}

// ----------------------

import java.io.Serializable;

public class Box implements Serializable, Cloneable{
	
//	INSTANCE;
//	
//	public void test() {
//		System.out.println("Test method");
//	}
	
	private static Box box;
	
	private Box() {	
//		if(box!=null) {
//			throw new RuntimeException("You are trying to break Singleton pattern.");
//		}
	}
	
//	Lazy way of creating singleton Object
	public static Box getBox() {
		
		if(box==null) {
			
			// So that 2 threads cannot access same thing at a particular time
			synchronized (Box.class) {
				if(box == null) {
					box = new Box();
				}
			}	
		}	
		return box;
	}
	
	public Object readResolve() {
		return box;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return box;
	}

}

