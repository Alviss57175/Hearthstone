package exception;
/**
 * exception générale concernant les problèmes liés à la physique d'Hearthstone
 *
 */
public class HearthstoneException extends Exception {

	public HearthstoneException(String msg){
		System.out.println(msg);
	}
}
