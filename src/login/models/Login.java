package login.models;

public class Login {
    private String tipoCliente;
    private String email;
    private String password;

    public Login(){}

    public Login(String tipoCliente, String email, String password) {
        this.tipoCliente = tipoCliente;
        this.email = email;
        this.password = password;
    }
    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;

}
}