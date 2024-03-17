package Console.Domain;

import java.util.ArrayList;
import java.util.List;

public class generareMasini {

    public static List<Masina> generateMasinaList(int numberOfObjects) {
        List<Masina> masinaList = new ArrayList<>();

        // Sample arrays for car brands and models (extended)
        String[] carBrands = {"BMW", "Toyota", "Ford", "Honda", "Tesla", "Chevrolet", "Mercedes", "Audi", "Volkswagen", "Nissan",
                "Subaru", "Mazda", "Lexus", "Kia", "Hyundai", "Jaguar", "Land Rover", "Porsche", "Ferrari", "Lamborghini",
                "Volvo", "Buick", "Cadillac", "GMC", "Chrysler", "Dodge", "Jeep", "Ram", "Fiat", "Alfa Romeo",
                "Mitsubishi", "Infiniti", "Acura", "Lincoln", "Mini", "Smart", "Suzuki", "Aston Martin", "Bentley", "Bugatti",
                "Maserati", "McLaren", "Rolls-Royce", "Maybach", "Lotus", "Fisker", "Genesis", "Rivian", "Lucid", "Koenigsegg",
                "Rivian", "Lucid", "Koenigsegg", "Ariel", "Karma", "Faraday Future", "Rimac", "Byton", "Bollinger", "Canoo",
                "Rivian", "Lucid", "Koenigsegg", "Ariel", "Karma", "Faraday Future", "Rimac", "Byton", "Bollinger", "Canoo",
                "Rivian", "Lucid", "Koenigsegg", "Ariel", "Karma", "Faraday Future", "Rimac", "Byton", "Bollinger", "Canoo",
                "Rivian", "Lucid", "Koenigsegg", "Ariel", "Karma", "Faraday Future", "Rimac", "Byton", "Bollinger", "Canoo"};

        String[] carModels = {"X1", "Camry", "Mustang", "Civic", "Model S", "Camaro", "C-Class", "A3", "Golf", "Altima",
                "Outback", "CX-5", "RX", "Soul", "Elantra", "F-Type", "Range Rover", "911", "488 GTB", "Aventador",
                "XC90", "Enclave", "Escalade", "Sierra", "300", "Challenger", "Wrangler", "1500", "500", "Giulia",
                "Outlander", "QX50", "MDX", "Navigator", "Cooper", "Fortwo", "SX4", "DB11", "Continental GT", "Chiron",
                "GranTurismo", "570S", "Phantom", "Maybach S-Class", "Evora", "Karma Revero", "FF 91", "C_Two", "Taycan",
                "R1T", "Air", "C_Two", "Taycan", "R1T", "Air", "C_Two", "Taycan", "R1T", "Air",
                "C_Two", "Taycan", "R1T", "Air", "C_Two", "Taycan", "R1T", "Air", "C_Two", "Taycan", "R1T"};

        for (int i = 1; i <= numberOfObjects; i++) {
            // Generate a Masina object with a unique ID between 1 and 100
            // Use modulo operator to ensure that the index is within the bounds of the arrays
            Masina masina = new Masina(i, carBrands[(i - 1) % carBrands.length], carModels[(i - 1) % carModels.length]);
            masinaList.add(masina);
        }

        return masinaList;
    }

}
