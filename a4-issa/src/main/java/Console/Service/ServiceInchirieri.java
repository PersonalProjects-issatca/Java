package Console.Service;

import Console.Domain.Inchiriere;
import Console.Domain.Masina;
import Console.Exceptions.DoesntExistException;
import Console.Exceptions.DuplicateEntityException;
import Console.Exceptions.InchiriereValidator;
import Console.Exceptions.WrongDatesException;
import Console.Repository.IRepo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ServiceInchirieri {

    IRepo<Inchiriere> repoInchirieri;
    IRepo<Masina> repoMasini;

    public ServiceInchirieri(IRepo<Inchiriere> repoInchirieri, IRepo<Masina> repoMasini){
        this.repoInchirieri = repoInchirieri;
        this.repoMasini = repoMasini;
    }

    public void adaugaInchiriere(int idi, int idm, String di, String ds) throws DoesntExistException, DuplicateEntityException, WrongDatesException {
        //if(verifMasina(idm) == -1)
        //throw new DoesntExistException("masina nu exista\n");

        /*if (repoInchirieri.getAll() != null) {
            for (Inchiriere i : repoInchirieri.getAll()) {
                if (i.getIdMasina() == idm) {
                    InchiriereValidator inchiriereValidator = new InchiriereValidator();
                    String line1 = i.getData_inceput();
                    String line2 = i.getData_sfarsit();
                    System.out.print(line1 + line2 + di + ds);
                    inchiriereValidator.verifInchiriere(line1, line2, di, ds);
                }
            }
        }*/

        Inchiriere i = new Inchiriere(idi, idm, di, ds);
        repoInchirieri.add(i);
    }

    public void modificaInchiriere(int idi, int idm, String di, String ds) throws DoesntExistException, WrongDatesException {
        /*if(repoMasini.getById(idm) == null)
            throw new DoesntExistException("masina nu exista\n");

        if (repoInchirieri.getAll() != null) {
            for (Inchiriere i : repoInchirieri.getAll()) {
                if (i.getIdMasina() == idm) {
                    InchiriereValidator inchiriereValidator = new InchiriereValidator();
                    String line1 = i.getData_inceput();
                    String line2 = i.getData_sfarsit();
                    System.out.print(line1 + line2 + di + ds);
                    inchiriereValidator.verifInchiriere(line1, line2, di, ds);
                }
            }
        }*/

        Inchiriere i = new Inchiriere(idi, idm, di, ds);
        repoInchirieri.update(i);
    }

    public Inchiriere getInchiriere(int id){
        return repoInchirieri.getById(id);
    }
    public void stergeInchiriere(int idi) throws IOException, DoesntExistException {
        repoInchirieri.delete(idi);
    }

    public void stergeMasina(int idm) throws IOException, DoesntExistException {
        int poz = verifMasina(idm);
        if(poz != -1){
            while( poz != -1){
                repoInchirieri.delete(poz);
                poz = verifMasina(idm);
            }
        }
    }

    public int verifMasina(int idm){
        if(repoInchirieri.getAll()!=null)
            for(Inchiriere i: repoInchirieri.getAll())
                if(i.getIdMasina() == idm) return i.getId();
        return -1;
    }

    public Masina getMasina(int idi){

        return repoMasini.getById(repoInchirieri.getById(idi).getIdMasina());
    }
    public ArrayList<Inchiriere> getInchirieri(){return repoInchirieri.getAll();}

    public int nrInchirieri(){
        return repoInchirieri.size();
    }

    public int nrInchirieriMasina(int idm){
        int cont=0;
        if(getInchirieri() != null)
            for(Inchiriere inchiriere:getInchirieri()) {
                if (inchiriere.getIdMasina() == idm) cont += 1;
            }
        return cont;
    }

    public int nrInchirieriLuna(int luna, int an){
        int cont=0;
        for(Inchiriere inchiriere:getInchirieri()) {
            String datai = inchiriere.getData_inceput();
            int lunac = Integer.parseInt(datai.split("-")[1]);
            int anc = Integer.parseInt(datai.split("-")[2]);
            if(lunac == luna && anc == an){
                cont++;
            }
        }
        return cont;
    }

    public int nrZileMasina(int idm){
        int nrzile=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for(Inchiriere inchiriere:getInchirieri()){
            if(inchiriere.getIdMasina()==idm){

                try {
                    Date datai = dateFormat.parse(inchiriere.getData_inceput());
                    Date dataf = dateFormat.parse(inchiriere.getData_sfarsit());
                    // Calculate the difference in milliseconds
                    long differenceInMilliseconds = dataf.getTime() - datai.getTime();

                    // Convert milliseconds to days
                    long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
                    nrzile+=differenceInDays;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return nrzile;
    }

    public ArrayList<String> pb1(){
        ArrayList<Masina> masini = repoMasini.getAll();
        ArrayList<Integer> c = new ArrayList<Integer>();
        for(Masina masina: repoMasini.getAll()){
            c.add(nrInchirieriMasina(masina.getId()));
        }
        for(int i=0;i<c.size()-1;i++){
            for(int j=i+1;j<c.size();j++)
                if(c.get(j)>c.get(i)) {
                    int aux = c.get(i);
                    c.set(i,c.get(j));
                    c.set(j,aux);

                    Masina auxi = masini.get(i);
                    masini.set(i, masini.get(j));
                    masini.set(j,auxi);
                }
        }
        ArrayList<String> rez = new ArrayList<>();
        for(int i=0;i<c.size();i++){
            Masina mc = masini.get(i);
            rez.add(mc+"  nr. inchirieri: "+c.get(i)+"\n");
        }
        return rez;
    }

    public ArrayList<String> pb2(){
        ArrayList<Integer> luni = new ArrayList<Integer>();
        ArrayList<Integer> ani = new ArrayList<Integer>();
        ArrayList<Integer> c = new ArrayList<Integer>();
        for(int i=2020;i<2024;i++)
            for(int j=1;j<=12;j++)
            {
                int cont = nrInchirieriLuna(j,i);
                if(cont!=0){
                    luni.add(j);
                    ani.add(i);
                    c.add(cont);
                }
            }
        for(int i=0;i<c.size()-1;i++){
            for(int j=i+1;j<c.size();j++)
                if(c.get(j)>c.get(i)) {
                    int aux = c.get(i);
                    c.set(i,c.get(j));
                    c.set(j,aux);

                    aux = luni.get(i);
                    luni.set(i,luni.get(j));
                    luni.set(j,aux);

                    aux = ani.get(i);
                    ani.set(i,ani.get(j));
                    ani.set(j,aux);
                }
        }
        ArrayList<String> rez = new ArrayList<>();
        for(int i=0;i<c.size();i++){
            rez.add("luna: "+luni.get(i)+", an: "+ ani.get(i) +", nr. inchirieri: "+c.get(i)+"\n");
        }
        return rez;
    }


    public ArrayList<String> pb3(){
        ArrayList<Masina> masini = repoMasini.getAll();
        ArrayList<Integer> c = new ArrayList<Integer>();
        for(Masina masina: repoMasini.getAll()){
            c.add(nrZileMasina(masina.getId()));
        }
        for(int i=0;i<c.size()-1;i++){
            for(int j=i+1;j<c.size();j++)
                if(c.get(j)>c.get(i)) {
                    int aux = c.get(i);
                    c.set(i,c.get(j));
                    c.set(j,aux);

                    Masina auxi = masini.get(i);
                    masini.set(i, masini.get(j));
                    masini.set(j,auxi);
                }
        }
        ArrayList<String> rez = new ArrayList<>();
        for(int i=0;i<c.size();i++){
            Masina mc = masini.get(i);
            rez.add(mc+"  nr. zile inchirieri: "+c.get(i)+"\n");
        }
        return rez;
    }

}
