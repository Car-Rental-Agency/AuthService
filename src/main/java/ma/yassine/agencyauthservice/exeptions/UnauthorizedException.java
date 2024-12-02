package ma.yassine.agencyauthservice.exeptions;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);
    }
}
