package Console;

import Console.Domain.Inchiriere;
import Console.Domain.Masina;
import Console.Repository.BDInchiriereRepo;
import Console.Repository.BDMasinaRepo;
import Console.Repository.IRepo;
import Console.Repository.MemoryRepo;
import Console.Service.ServiceInchirieri;
import Console.Service.ServiceMasini;
import Console.UI.Console;

public class Main {

    public static void main(String[] args) {

        BDMasinaRepo masini = new BDMasinaRepo();
        BDInchiriereRepo inchirieri = new BDInchiriereRepo();

        IRepo<Masina> repoMasini = new MemoryRepo<>();
        IRepo<Inchiriere> repoInchirieri = new MemoryRepo<>();
        ServiceMasini serviceMasini = new ServiceMasini(repoMasini);
        ServiceInchirieri serviceInchirieri = new ServiceInchirieri(repoInchirieri, repoMasini);
        Console c = new Console(serviceMasini,serviceInchirieri);
        c.meniu();

    }

}
