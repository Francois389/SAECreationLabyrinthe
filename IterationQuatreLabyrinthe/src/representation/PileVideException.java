/**
 * PileVideException.java                       23 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package representation;

/**
 * 
 * @author francois.desaintpala
 *
 */
public class PileVideException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
    public PileVideException() {
        super();
    }

	/**
	 *
	 */
    public PileVideException(String message) {
        super(message);
    }

	

}
