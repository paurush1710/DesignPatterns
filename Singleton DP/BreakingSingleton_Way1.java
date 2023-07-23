public class Main {

	public static void main(String[] args) throws Exception{
	
//		1. Reflection API to break singleton pattern
//		Solution: 1-> if object is there ==> throw Exception from inside constructor
//		2. Use Enum
		
		Box b = Box.getBox(); 
		System.out.println(b.hashCode());
		
		Constructor<Box> constructor = Box.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Box b2 = constructor.newInstance();
		System.out.println(b2.hashCode());

	}

}

//--------------------------------------------------------------

public class Box {
	
	private static Box box;
	
	private Box() {	
		if(box!=null) {
			throw new RuntimeException("You are trying to break Singleton pattern.");
		}
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

}
