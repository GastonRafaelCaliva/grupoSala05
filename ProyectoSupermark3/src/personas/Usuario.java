package personas;

public class Usuario {
	protected String nombreUsuario;
	private String contraseñaUsuario;
	public Usuario() {
		
	}
	public Usuario(String nombreUsuario, String contraseñaUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.contraseñaUsuario = contraseñaUsuario;
	}
	public String getUsername() {
		return nombreUsuario;
	}
	public String getPassword() {
		return contraseñaUsuario;
	}
}
