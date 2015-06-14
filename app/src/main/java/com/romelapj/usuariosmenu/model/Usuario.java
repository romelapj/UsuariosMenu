package com.romelapj.usuariosmenu.model;

/**
 * Created by romelapj on 9/06/15.
 */
public class Usuario {
    private String nombre;
    private String usuario;
    private String password;

    public Usuario(String nombre, String usuario, String password) {
        this.nombre=nombre;
        this.usuario = usuario;
        this.password = password;
    }
    public Usuario(String nombre, String usuario) {
        this.nombre=nombre;
        this.usuario = usuario;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}