package Modell;

/**
 * Created by P&D on 7/05/2018.
 */

public class usuario {
    private String id_, login, clave, estado;
    public usuario(){}
    public usuario(String id, String login, String clave, String estado) {
        this.id_ = id;
        this.login = login;
        this.clave = clave;
        this.estado = estado;
    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
