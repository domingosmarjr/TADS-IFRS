import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]){
        List<IDirigir> drivers = new ArrayList<>();
        drivers.add(new Pessoa());
        drivers.add(new Robo());

        for(IDirigir i: drivers) {
            i.dirigir();
        }


    }
}