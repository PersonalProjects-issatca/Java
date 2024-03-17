package Console.Domain;

import java.io.Serializable;

public class Masina extends Entitate implements Serializable {

    private static final long serialVersionUID = 1L;
    String model, marca;

    //public static final long serialVersionUID = 1234L; implements Serializable
    public Masina(int id, String marca, String model){
        super(id);
        this.marca=marca;
        this.model=model;
    }
    public String getModel(){
        return this.model;
    }

    public String getMarca(){
        return this.marca;
    }

    public void setModel(String s){
        this.model = s;
    }

    public void setMarca(String s){
        this.marca = s;
    }

    public String toString(){
        return "id: "+this.id+", marca: "+this.marca+", model: "+this.model+" \n";
    }
    @Override
    public String toStringFile(){return this.id+","+this.marca+","+this.model+"\n";}

}
