package com.example.crudsqlite.entidades;

public class Usuario {
    int Id;
    String Usuario, Password;

    public Usuario() {
    }

    public Usuario(String usuario, String password) {
        Usuario = usuario;
        Password = password;
    }

    public boolean isNull(){
        if (Usuario.equals("")&&Password.equals("")){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
