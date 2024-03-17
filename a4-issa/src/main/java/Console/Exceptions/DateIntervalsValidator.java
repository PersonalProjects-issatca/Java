package Console.Exceptions;

public class DateIntervalsValidator {

    public void verifDateIntervalsValidator(String line1,String line2) throws InvalidYearException, InvalidMonthException, InvalidDayException {

        int zi1 = Integer.parseInt(line1.split("-")[0]);
        int luna1 = Integer.parseInt(line1.split("-")[1]);
        int an1 = Integer.parseInt(line1.split("-")[2]);

        int zi2 = Integer.parseInt(line2.split("-")[0]);
        int luna2 = Integer.parseInt(line2.split("-")[1]);
        int an2 = Integer.parseInt(line2.split("-")[2]);

        if(an2<an1)
            throw new InvalidYearException("anul de la data de sfarsit este mai mic decat anul de la data de inceput\n");

        if(an1==an2 && luna2<luna1)
            throw new InvalidMonthException("luna de la data de sfarsit este mai mica decat luna de la data de inceput\n");

        if(an1==an2 && luna1==luna2 && zi2<zi1)
            throw new InvalidDayException("ziua de la data de sfarsit este mai mica decat ziua de la data de inceput\n");

    }

}
