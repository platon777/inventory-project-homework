package ClasseJava;

public class Login {
	private final String USER = "H";
	private final String PASSWORD = "1";
	private String user;
	private String password;
	
	public Login (String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public boolean verification () {
		if(user.equals(USER) && password.equals(PASSWORD)) {
			return true;
		}
		return false;
	}
}
