package star_battle.exceptions;

public class InvalidStarsNumberException extends RuntimeException {
    public InvalidStarsNumberException(String errorMessage){
        super(errorMessage);
    }
}
