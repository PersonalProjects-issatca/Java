package Console.UI;

import Console.Exceptions.*;
import Console.Service.ServiceInchirieri;
import Console.Service.ServiceMasini;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Console {

    ServiceMasini serviceMasini;
    ServiceInchirieri serviceInchirieri;

    public Console(ServiceMasini serviceMasini, ServiceInchirieri serviceInchirieri){
        this.serviceMasini = serviceMasini;
        this.serviceInchirieri = serviceInchirieri;
    }

    void adaugaMasina(){
        System.out.printf("da id-ul masinii: ");
        Scanner in = new Scanner(System.in);
        String id = in.nextLine();
        try{
            int number = Integer.parseInt(id);
            System.out.printf("da marca masinii: ");
            String marca=in.nextLine();
            System.out.printf("da modelul masinii: ");
            String model=in.nextLine();
            serviceMasini.adaugaMasina(number,marca,model);
        }
        catch (DuplicateEntityException ex){ System.out.printf(ex.getMessage()); }
        catch (NumberFormatException ex) {
            //ex.printStackTrace();
            System.out.print("id-ul trebuie sa fie un numar natural\n");
        }
        catch (IOException ex){}
        catch (Exception ex){}
    }

    void modificaMasina(){
        System.out.printf("da id-ul masinii de modificat: ");
        Scanner in = new Scanner(System.in);
        String id = in.nextLine();
        try{
            int number = Integer.parseInt(id);
            System.out.printf("da marca masinii noi: ");
            String marca=in.nextLine();
            System.out.printf("da modelul masinii noi: ");
            String model=in.nextLine();
            serviceMasini.modificaMasina(number,marca,model);
        }
        catch (DoesntExistException ex){ System.out.printf(ex.getMessage()); }
        catch (NumberFormatException ex) {
            //ex.printStackTrace();
            System.out.print("id-ul trebuie sa fie un numar natural\n");
        }
        catch (Exception ex){}
    }

    void stergeMasina(){
        System.out.printf("da id-ul masinii: ");
        Scanner in = new Scanner(System.in);
        String id = in.nextLine();
        try{
            int number = Integer.parseInt(id);
            serviceMasini.stergeMasina(number);
            serviceInchirieri.stergeMasina(number);
        }
        catch (DoesntExistException ex){ System.out.printf(ex.getMessage()); }
        catch (NumberFormatException ex) { System.out.print("id-ul trebuie sa fie un numar natural\n");}
        catch (IOException ex){}
        catch (Exception ex){}
    }


    void adaugaInchiriere(){
        System.out.printf("da id-ul inchirierii: ");
        Scanner in = new Scanner(System.in);
        String id1 = in.nextLine();
        System.out.printf("da id-ul masinii inchiriate: ");
        String id2 = in.nextLine();
        try{
            int idi = Integer.parseInt(id1);
            int idm = Integer.parseInt(id2);

            DateValidator dateValidator = new DateValidator();
            DateIntervalsValidator dateIntervalsValidator = new DateIntervalsValidator();
            System.out.printf("introdu data de inceput (zi-luna-an): ");
            String di = in.nextLine();
            dateValidator.verifData(di);

            System.out.printf("introdu data de sfarsit (zi-luna-an): ");
            String df = in.nextLine();
            dateValidator.verifData(df);

            dateIntervalsValidator.verifDateIntervalsValidator(di,df);


            serviceInchirieri.adaugaInchiriere(idi, idm, di, df);

        }
        catch (DoesntExistException | WrongDatesException | DuplicateEntityException | WrongDateFormatException | InvalidYearException | InvalidMonthException |
               InvalidDayException ex){ System.out.printf(ex.getMessage()); }
        catch (NumberFormatException ex) {
            //ex.printStackTrace();
            System.out.print("id-ul trebuie sa fie un numar natural\n");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    void modificaInchiriere(){
        System.out.printf("da id-ul inchirierii de modificat: ");
        Scanner in = new Scanner(System.in);
        String id1 = in.nextLine();
        System.out.printf("da id-ul masinii inchiriate: ");
        String id2 = in.nextLine();
        try{
            int idi = Integer.parseInt(id1);
            int idm = Integer.parseInt(id2);

            DateValidator dateValidator = new DateValidator();
            DateIntervalsValidator dateIntervalsValidator = new DateIntervalsValidator();
            System.out.printf("introdu data de inceput (zi-luna-an): ");
            String di = in.nextLine();
            dateValidator.verifData(di);

            System.out.printf("introdu data de sfarsit (zi-luna-an): ");
            String df = in.nextLine();
            dateValidator.verifData(df);

            dateIntervalsValidator.verifDateIntervalsValidator(di,df);

            serviceInchirieri.modificaInchiriere(idi,idm,di,df);
        }
        catch (DoesntExistException | WrongDatesException | WrongDateFormatException | InvalidYearException | InvalidMonthException |
               InvalidDayException ex){ System.out.printf(ex.getMessage()); }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.print("id-ul trebuie sa fie un numar natural\n");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    void stergeInchiriere(){
        System.out.printf("da id-ul inchirierii: ");
        Scanner in = new Scanner(System.in);
        String id1 = in.nextLine();
        try{
            int idi = Integer.parseInt(id1);
            serviceInchirieri.stergeInchiriere(idi);
        }
        catch (DoesntExistException ex){ System.out.printf(ex.getMessage()); }
        catch (NumberFormatException ex) {
            //ex.printStackTrace();
            System.out.print("id-ul trebuie sa fie un numar natural\n");
        }
        catch (Exception ex){}
    }


    static void print_meniu(){
        System.out.printf("1. adauga masina\n");
        System.out.printf("2. modifica masina\n");
        System.out.printf("3. sterge masina\n");
        System.out.printf("m. afiseaza masinile\n");
        System.out.printf("4. adauga inchiriere\n");
        System.out.printf("5. modifica inchiriere\n");
        System.out.printf("6. sterge inchiriere\n");
        System.out.printf("i. afiseaza inchirierile\n");
        System.out.printf("x. iesire din aplicatie\n");
    }

    public void meniu() {
        while(true){
            print_meniu();
            Scanner in = new Scanner(System.in);
            String optiune = in.nextLine();
            //System.out.print(optiune + "\n");
            if(Objects.equals(optiune, "1")){
                adaugaMasina();
            }
            else if(Objects.equals(optiune, "2")){
                modificaMasina();
            }
            else if(Objects.equals(optiune, "3")){
                stergeMasina();
            }
            else if(Objects.equals(optiune, "m")){
                System.out.print(serviceMasini.getMasini()+"\n");
            }
            else if(Objects.equals(optiune, "4")){
                adaugaInchiriere();
            }
            else if(Objects.equals(optiune, "5")){
                modificaInchiriere();
            }
            else if(Objects.equals(optiune, "6")){
                stergeInchiriere();
            }
            else if(Objects.equals(optiune, "i")){
                System.out.print(serviceInchirieri.getInchirieri()+"\n");
            }
            else if(Objects.equals(optiune, "x")){
                break;
            }
            else{
                System.out.printf("optiune gresita\n");
            }
        }
    }
}
