package exceptions;

/** 
 * exception levee si une pile est vide
 * @author Denamiel Clement
 */
public class PileVideException extends RuntimeException {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PileVideException() {
		super();
	}

	public PileVideException(String message) {
		super(message);
	}
	
}
