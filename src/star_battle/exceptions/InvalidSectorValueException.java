package star_battle.exceptions;

public class InvalidSectorValueException extends RuntimeException{
    public InvalidSectorValueException(String errorMessage){
        super(errorMessage);
    }
}
