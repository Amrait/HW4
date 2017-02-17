package HW4Task1;

import java.util.HashMap;

/**
 * Created by Олексій on 15.02.2017.
 */
public class Outlet {
    public String Name; //назва відділення
    public HashMap<String,Integer> Distances;//відстані до інших відділень по іменам. Поки що найкраще, що спало на думку
    volatile public int Packages;//кількість посилок у відділенні
    volatile public int Trucks;//доступний автопарк
    public int getTrucks() {
        return Trucks;
    }
    public Outlet(){
        this.Name = "Name is not set";
        this.Distances = new HashMap<>();
    }
    public Outlet(String name){
        this.Name = name;
        this.Distances = new HashMap<>();
    }
    //створюємо відділення
    public Outlet(String name, HashMap<String,Integer> distances, int packages, int trucks){
        this.Name = name;
        this.Distances = new HashMap<>(distances);
        this.Packages = packages;
        this.Trucks = trucks;
    }
}
