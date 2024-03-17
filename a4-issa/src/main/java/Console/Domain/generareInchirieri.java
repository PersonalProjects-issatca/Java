package Console.Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class generareInchirieri
{


    public static List<Inchiriere> generateInchiriereList(int numberOfObjects)
    {
        List<Inchiriere> inchiriereList = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= numberOfObjects; i++)
        {
            // Generate a random Masina ID between 1 and 100
            int randomIdMasina = random.nextInt(100) + 1;

            // Generate random dates between 1-1-2021 and 1-1-2024
            String randomDataInceput = generateRandomDate("01-01-2021", "01-01-2024");
            String randomDataSfarsit = generateRandomDate(randomDataInceput, "01-01-2024");

            // Generate an Inchiriere object with a unique ID between 1 and 100
            Inchiriere inchiriere = new Inchiriere(i, randomIdMasina, randomDataInceput, randomDataSfarsit);
            inchiriereList.add(inchiriere);
        }

        return inchiriereList;
    }

    private static String generateRandomDate(String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long randomDate = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
            return sdf.format(new Date(randomDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String generateRandomDateAfter(String startDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date start = sdf.parse(startDate);

            // Ensure that the random date is at least one day after the start date
            long randomDate = start.getTime() + 86400000 + (long) (Math.random() * (System.currentTimeMillis() - start.getTime() - 86400000));
            return sdf.format(new Date(randomDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
