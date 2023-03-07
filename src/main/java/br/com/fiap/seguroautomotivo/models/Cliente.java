package br.com.fiap.seguroautomotivo.models;

public class Cliente {
    
    private String email;

    private String senha;

    
    public Cliente() {
    }

    
    public Cliente(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
