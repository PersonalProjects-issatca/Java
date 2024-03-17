package Console.Exceptions;

public class InchiriereValidator {

    public void verifInchiriere(String line1, String line2, String line3, String line4) throws WrongDatesException {
        //data inceput inchiriere gasita
        int zi1 = Integer.parseInt(line1.split("-")[0]);
        int luna1 = Integer.parseInt(line1.split("-")[1]);
        int an1 = Integer.parseInt(line1.split("-")[2]);
        //data sfarsit inchiriere gasita
        int zi2 = Integer.parseInt(line2.split("-")[0]);
        int luna2 = Integer.parseInt(line2.split("-")[1]);
        int an2 = Integer.parseInt(line2.split("-")[2]);
        //data inceput inchiriere adaugata
        int zi3 = Integer.parseInt(line3.split("-")[0]);
        int luna3 = Integer.parseInt(line3.split("-")[1]);
        int an3 = Integer.parseInt(line3.split("-")[2]);
        //data sfarsit inchiriere adaugata
        int zi4 = Integer.parseInt(line4.split("-")[0]);
        int luna4 = Integer.parseInt(line4.split("-")[1]);
        int an4 = Integer.parseInt(line4.split("-")[2]);

        if (an1==an2 && an1==an3 && an1== an4 && (luna2==luna3 || luna1==luna4))
            if (zi3<=zi2 && zi4>=zi1)
                throw new WrongDatesException("datele introduse se suprapun cu o alta inchiriere\n");
            else return;

        if (an2 == an3 || an1 == an4)
            if( luna3<=luna2 && luna4>=luna1)
                throw new WrongDatesException("datele introduse se suprapun cu o alta inchiriere\n");
            else return;

        if( an3<=an2 && an4>=an1 )
            throw new WrongDatesException("datele introduse se suprapun cu o alta inchiriere\n");

    }

}
