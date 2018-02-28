package strategy;

public class MuteQuack implements QuackBehaviour{
	public void quack()
	{
		System.out.println("<<silence>>");
	}
}
