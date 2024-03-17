package Console.Exceptions;

public class DateValidator {

    public void verifData(String line) throws NumberFormatException, WrongDateFormatException, InvalidYearException, InvalidMonthException, InvalidDayException {
        if (line.split("-").length != 3)
            throw new WrongDateFormatException("data introdusa nu respecta formatul cerut (zi-luna-an)\n");

        try {
            int zi = Integer.parseInt(line.split("-")[0]);
            int luna = Integer.parseInt(line.split("-")[1]);
            int an = Integer.parseInt(line.split("-")[2]);

            if (an < 2020 || an > 2023)
                throw new InvalidYearException("anul introdus nu este valid, el trebuie sa fie un nr cuprins intre 2020 si 2023\n");

            if (luna < 0 || luna > 12)
                throw new InvalidMonthException("luna introdusa nu este valida, ea trebuie sa fie un nr cuprins intre 1 si 12\n");

            if (zi < 0 || zi > 31)
                throw new InvalidDayException("ziua introdusa nu este valida, ea trebuie sa fie un nr cuprins intre 1 si 31 in functie de luna\n");

            if (zi > 29 && luna == 2)
                throw new InvalidDayException("pentru luna introdusa ziua este un nr cuprins intre 1 si 29\n");

            if (zi == 29 && luna == 2 && an % 400 != 0 && (an % 4 != 0 || an % 100 == 0))
                throw new InvalidDayException("pentru anul introdus ziua este un nr cuprins intre 1 si 28\n");

            if (zi == 31 && (luna == 4 || luna == 6 || luna == 9 || luna == 11))
                throw new InvalidDayException("pentru luna introdusa ziua este un nr cuprins intre 1 si 30\n");
        }
        catch (NumberFormatException ex) {throw new WrongDateFormatException("data introdusa contine caractere\n");}
    }

}
