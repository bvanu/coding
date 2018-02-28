package strategy;

public class MallardDuck extends Duck{

	public MallardDuck()
	{
		flyBehaviour = new FlyWithWings();
		quackBehaviour = new Quack();
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("I am a Mallard Duck!");
	}
}
