import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UniqueNumbers
{
    private static Set<String> readFileToSet(String filePath) throws IOException {
        Set<String> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line.trim());
            }
        }
        return set;
    }

    public static List<String> distinctNumbers( Set<String> set1, Set<String> set2){
        List<String> distinct = new ArrayList<>();

        for(String key : set1){
            if(!set2.contains(key)){
                distinct.add(key);
            }
        }
        return distinct;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hello");
        Set<String> set1 = readFileToSet("file1.txt");
        //System.out.print(set1);
        Set<String> set2 = readFileToSet("file2.txt");
        //System.out.print(set2);
        List<String> distinctOfFile1 = distinctNumbers(set1,set2);
        List<String> distinctOfFile2 = distinctNumbers(set2,set1);
        System.out.println(distinctOfFile1);
        System.out.println(distinctOfFile2);
    }
}
