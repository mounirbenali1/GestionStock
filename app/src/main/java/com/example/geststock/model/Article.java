package com.example.geststock.model;

public class  Article {
    private int id;
    private String libelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Article() {
    }

    public Article( String libelle) {
        //this.id = id;
        this.libelle = libelle;
    }
    public Article( String libelle, int id) {
        this.id = id;
        this.libelle = libelle;
    }



    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
