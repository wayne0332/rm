package cn.jason.rm.exception;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-3-5
 */
public class ControllerException extends RuntimeException
{
	public ControllerException()
	{
	}

	public ControllerException(String message)
	{
		super(message);
	}

	public ControllerException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ControllerException(Throwable cause)
	{
		super(cause);
	}
}
