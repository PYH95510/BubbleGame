package bubble.test.ex00;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
class Dog {
	private String name;

}
public class LombokTest {
	

	
	public static void main(String[] args) {
		
		Dog d = new Dog();
		
		d.setName("toto");
		
		System.out.println(d.getName());
		
		
		
		
//	a.setName("Toto");
//	System.out.println(a.getName());

	}
}