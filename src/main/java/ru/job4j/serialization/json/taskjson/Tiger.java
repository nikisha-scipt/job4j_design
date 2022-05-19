package ru.job4j.serialization.json.taskjson;

import java.util.Arrays;
import java.util.Objects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "tiger")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tiger {

    @XmlAttribute
    private boolean isFly;
    @XmlAttribute
    private int age;
    private String name;
    private Animal type;

    @XmlElementWrapper(name = "location")
    @XmlElement(name = "loc")
    private String[] location;

    public Tiger(boolean isFly, int age, String name, Animal type, String[] location) {
        this.isFly = isFly;
        this.age = age;
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public Tiger() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tiger tiger = (Tiger) o;
        return isFly == tiger.isFly && age == tiger.age && Objects.equals(name, tiger.name) && Objects.equals(type, tiger.type) && Arrays.equals(location, tiger.location);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isFly, age, name, type);
        result = 31 * result + Arrays.hashCode(location);
        return result;
    }

    @Override
    public String toString() {
        return "Tiger{" + "isFly=" + isFly + ", age=" + age + ", name='" + name + '\'' + ", type=" + type + ", location=" + Arrays.toString(location) + '}';
    }

    public void jsonCreate() {
        final Tiger tiger = new Tiger(false, 5, "lion", new Animal("Cat"), new String[] {"Bangladesh", "Vietnam", "India"});

        final Gson gson = new GsonBuilder().create();
        String jsonStr = gson.toJson(tiger);
        System.out.println(jsonStr);

        final Tiger tigerTemp = gson.fromJson(jsonStr, Tiger.class);
        if (tigerTemp.equals(tiger)) {
            System.out.println(tigerTemp);
        }
    }

}
