import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class parsing {
    private static String comDel = ",";
    private static String userInput = "a";

    public static void main(String[] args) {
        List<List<String>> parentList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("sample.csv"))) {
            while (sc.hasNextLine()) {
                parentList.add(addRows(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (List<String> fileName : parentList) {
            names.add(fileName.get(1));
            names.add(fileName.get(2));
        }
        androidDevs(parentList);
        NameCounter(names, userInput);
        TimeSamples(parentList, names);
    }

    public static List<String> addRows(String fileLine) {
        List<String> lines = new ArrayList<String>();
        try (Scanner scRows = new Scanner(fileLine)) {
            scRows.useDelimiter(comDel);
            while (scRows.hasNext()) {
                lines.add(scRows.next());
            }
        }
        return lines;
    }

    private static void androidDevs(List<List<String>> parentList) {
        List<String> androids = new ArrayList<>();
        int i = 0;
        for (List<String> users : parentList) {
            androids.add(users.get(6));
        }
        for (String android : androids) {
            if (android.equals("Android App")) {
                i++;
            }
        }
        System.out.println(i + " has chosen to develope in Android");
    }

    private static void NameCounter(List<String> names, String userIn) {
        int nameCount = 0;
        for (int i = 2; i < names.size(); i++) {
            if (names.get(i).contains(userIn) || names.get(i).contains(userIn.toUpperCase())) {
                nameCount++;
            }
        }
        System.out.println(nameCount + " people has the letter " + userIn + " in them.");
    }

    public static void TimeSamples(List<List<String>> parentList, List<String> names) {
        List<String> dateTime = new ArrayList<>();
        List<String> uniqueDateTime = new ArrayList<>();
        List<String> sameDateTime = new ArrayList<>();
        List<String> namesOne = new ArrayList<>();
        List<String> namesTwo = new ArrayList<>();
        List<String> dateNames = new ArrayList<>();

        int index = 0;

        for (List<String> line : parentList) {
            dateTime.add(line.get(0));
            namesOne.add(line.get(1));
            namesTwo.add(line.get(2));
            if (dateTime.get(index).equals("")) {
                uniqueDateTime.add(dateTime.get(index));
            } else if (uniqueDateTime.contains(dateTime.get(index))) {
                sameDateTime.add(dateTime.get(index));
            } else {
                uniqueDateTime.add(dateTime.get(index));
            }
            index++;
        }

        Boolean isTrue = true;

        for (String x : sameDateTime) {
            for (int i = 0; i < dateTime.size(); i++) {
                if (x.equals(dateTime.get(i))) {
                    dateNames.add(namesOne.get(i));
                    dateNames.add(" and ");
                    dateNames.add(namesTwo.get(i));
                    if (isTrue) {
                        dateNames.add(x);
                        dateNames.add(" has the same timestamp as ");
                        isTrue = false;
                    }
                }
            }
            isTrue = true;
            System.out.println(dateNames);
            dateNames.removeAll(dateNames);
        }
    }

}