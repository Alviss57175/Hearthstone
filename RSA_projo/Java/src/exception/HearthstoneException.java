package exception;
/**
 * exception g�n�rale concernant les probl�mes li�s � la physique d'Hearthstone
 *
 */
public class HearthstoneException extends Exception {

	public HearthstoneException(String msg){
		System.out.println(msg);
	}
}
