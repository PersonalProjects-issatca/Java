package Console.Domain;

import java.text.SimpleDateFormat;

public class Inchiriere extends Entitate{

    int idMasina;
    String data_inceput;
    String data_sfarsit;

    public Inchiriere(int id, int idm, String di, String ds){
        super(id);
        this.idMasina = idm;
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        this.data_inceput = di;
        this.data_sfarsit = ds;
    }

    public int getIdMasina(){return this.idMasina;}

    public String getData_inceput(){return this.data_inceput;}

    public String getData_sfarsit(){return this.data_sfarsit;}

    public void setIdMasina(int m){this.idMasina=m;}

    public void setData_inceput(String di){this.data_inceput=di;}

    public void setData_sfarsit(String ds){this.data_sfarsit=ds;}

    public String toString(){
        return "id-ul inchirierii: " + this.getId() + "\n id masina inchiriata: "+ idMasina + "\n  data de inceput: " + data_inceput +
                "\n   data de sfarsit: " + data_sfarsit + "\n" ;
    }

    @Override
    public String toStringFile(){
        return this.getId() + ","+ idMasina + "," + data_inceput +"," + data_sfarsit + "\n";
    }
}
