package ma.enset.gestionabonnement.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object resourceId) {
        super("Resource not found with id " + resourceId);
    }
}
