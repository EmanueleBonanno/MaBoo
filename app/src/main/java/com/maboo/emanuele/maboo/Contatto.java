package com.maboo.emanuele.maboo;

/**
 * Created by Emanuele on 19/01/15.
 */
public class Contatto {

    private String ID;
    private String nome;
    private String nTelefono;


    public Contatto(){
        this.ID="";
        this.nome="";
        this.nTelefono="";
    }

    public Contatto(String ID,String nome,String nTelefono){
        this.ID=ID;
        this.nome=nome;
        this.nTelefono=nTelefono;
    }

    public String getID(){return this.ID;}

    public String nome(){return this.nome;}

    public String nTelefono(){return this.nTelefono;}

    public void setID(String ID){
        this.ID=ID;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void setnTelefono(String nTelefono){
        this.nTelefono=nTelefono;
    }
}
