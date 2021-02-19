package com.example.geststock.model;

public class Operation {
    private int num;
    private  String date;
    private int qte;
    private int article_id;
    public Operation() {

    }


    public Operation(int article_id, String date, int qte) {
        this.article_id = article_id;
        this.date = date;
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Operation{" +

                ", date='" + date + '\'' +
                ", qte=" + qte +
                '}';
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

