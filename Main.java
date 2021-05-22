import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(x -> x.getAge()<18)
                .count();
        System.out.println("Кол-во несовершеннолетних человек:" + count);

        List<String> result = persons.stream()
                .filter(a -> a.getAge()>18|| a.getAge()<27)
                .filter(s -> s.getSex() == Sex.MAN)
                .limit(30)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Фамилии призывников: " + result);

        List <String> worker = persons.stream()
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(y -> y.getAge() >=18)
                .filter(s -> s.getSex() == Sex.MAN && s.getAge() < 60 || s.getSex() ==Sex.WOMEN && s.getAge() < 65)
                .limit(30)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println("Фамилии работоспособных людей с высшим образованием: " + worker);

    }
}
