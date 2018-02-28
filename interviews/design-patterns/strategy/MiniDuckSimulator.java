package strategy;

public class MiniDuckSimulator {
	public static void main(String[] args)
	{
		Duck mallard = new MallardDuck();
		
		mallard.display();
		mallard.performFly();
		mallard.performQuack();
	}
}
