package db;

import models.Person;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDB {
    private static Map<ObjectId, Person> persons = new HashMap<>();
    private static final ObjectId id1 = new ObjectId("64b6c710171adc359ebcba3e");
    private static final ObjectId id2 = new ObjectId("64b6c71e54f44431a452bb17");
    private static final ObjectId id3 = new ObjectId("64b6c724ed632ec59fd867ae");
    private static final ObjectId id4 = new ObjectId("64b6c72b235971d6c2c87898");

    static {
        persons.put(id1, new Person(id1, "FN1", "LN1", "email1@email.com"));
        persons.put(id2, new Person(id2, "FN2", "LN2", "email2@email.com"));
        persons.put(id3, new Person(id3, "FN3", "LN3", "email3@email.com"));
        persons.put(id4, new Person(id4, "FN4", "LN4", "email4@email.com"));
    }

    public static Person getById(ObjectId id) {
        return persons.get(id);
    }

    public static List<Person> getAll() {
        List<Person> result = new ArrayList<Person>();
        for (ObjectId key : persons.keySet()) {
            result.add(persons.get(key));
        }
        return result;
    }

    public static int getCount() {
        return persons.size();
    }

    public static void remove() {
        if (!persons.keySet().isEmpty()) {
            persons.remove(persons.keySet().toArray()[0]);
        }
    }

    public static String save(Person person) {
        String result = "";
        if (persons.get(person.getId()) != null) {
            result = "Updated Person with id=" + person.getId();
        } else {
            result = "Added Person with id=" + person.getId();
        }
        persons.put(person.getId(), person);
        return result;
    }
}
