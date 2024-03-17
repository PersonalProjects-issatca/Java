package Console.Domain;

import java.io.Serializable;

public abstract class Entitate  implements Serializable
{
    private static final long serialVersionUID = 1L;

    int id;

    Entitate(){this.id = 0;}
    Entitate(int n){
        id=n;
    }
    public int getId(){
        return this.id;
    }

    public void setId(int n){
        this.id=n;
    }

    public abstract String toStringFile();

}
