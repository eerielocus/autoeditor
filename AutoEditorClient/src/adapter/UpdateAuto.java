package adapter;

import exception.ExceptionHandler;

public interface UpdateAuto
{
	public void updateOpSet(String name, String oldset, String newset) throws ExceptionHandler;
	public void updateOpPrice(String name, String opset, String op, int price) throws ExceptionHandler;
}
