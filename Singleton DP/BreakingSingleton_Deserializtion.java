package Classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class Main {

	public static void main(String[] args) throws Exception{

//		Box b1 = Box.getBox();
//		System.out.println(b1.hashCode());
//		
//		Box b2 = Box.getBox();
//		System.out.println(b2.hashCode());
	
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
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("abc.ob"));
		oos.writeObject(box);
		
		System.out.println("Serializtion Done ...");
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("abc.ob"));
		Box b2 = (Box)ois.readObject();
		System.out.println(b2.hashCode());
		
//		Box b = Box.INSTANCE; 
//		System.out.println(b.hashCode());
//		b.test();
//		
//		Constructor<Box> constructor = Box.class.getDeclaredConstructor();
//		constructor.setAccessible(true);
//		Box b2 = constructor.newInstance();
//		System.out.println(b2.hashCode());
		
		
		
	}

}

-----------------------

  package Classes;

import java.io.Serializable;

public class Box implements Serializable{
	
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

  // Method added so that after deserialization object return is same 
	public Object readResolve() {
		return box;
	}

}

