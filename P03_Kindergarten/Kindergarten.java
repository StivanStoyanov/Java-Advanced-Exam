package kindergarten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kindergarten {
    private String name;
    private int capacity;
    private List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>();
    }
    public boolean addChild(Child child){
        if (registry.size() < capacity){
            registry.add(child);
            return true;
        }
        return false;
    }
    public boolean removeChild(String firstName){

        for (Child children : this.registry) {

            if (children.getFirstName().equals(firstName)){
                this.registry.remove(children);

                return true;
            }
        }
        return false;
    }
    public int getChildrenCount(){
        return this.registry.size();
    }
    public Child getChild(String firstName){

        for (Child children : this.registry) {
            if (children.getFirstName().equals(firstName)){
                return children;
            }
        }
        return null;
    }

    public String registryReport(){
        StringBuilder sb = new StringBuilder();
        sb.append("Registered children in ").append(this.name).append(":\n");

        CompareChildren comparetor = new CompareChildren();

        Collections.sort(registry, (children1, children2) -> {
            return children1.getAge() - children2.getAge();});

        for (Child children : this.registry) {

            sb.append(children).append("\n");
        }

        Collections.sort(this.registry);
        return sb.toString();
    }
}
