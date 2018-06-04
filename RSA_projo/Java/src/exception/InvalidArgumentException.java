package exception;
/**
 * Exception concernant les erreurs d'arguments.
 *
 */
public class InvalidArgumentException extends Exception {
	public InvalidArgumentException(){
		System.out.println("Argument Invalide");
	}
}
