
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Lets Play Flames!");
        System.out.print("Enter the 1st name:");
        Scanner sc = new Scanner(System.in);
        String name1 = sc.nextLine();
        System.out.print("Enter the 2nd name:");
        String name2 = sc.nextLine();
        name1 = name1.toLowerCase().replace(" ", "");
        name2 = name2.toLowerCase().replace(" ", "");
        int commonCount = calculateCommonChar(name1,name2);
        int reqCount = name1.length()+name2.length()-2 * commonCount;
        int index = 0;
        List<Character> flames = new ArrayList<>(Arrays.asList('F','L','A','M','E','S'));
        while(flames.size()>1)
        {
            index = (index + reqCount - 1)%flames.size();
            flames.remove(index);

        }
        System.out.println("Output:"+flames.get(0));
    }

    private static int calculateCommonChar(String name1, String name2) {
        int count = 0;
        Map<Character,Integer> freq = new HashMap<>();
        for(int i=0;i<name1.length();i++)
            freq.put(name1.charAt(i),freq.getOrDefault(name1.charAt(i),0)+1);

        for(int i=0;i<name2.length();i++)
        {
            char ch = name2.charAt(i);
            if(freq.containsKey(ch))
                if(freq.get(ch) > 0)
                {
                    count++;
                    freq.put(ch,freq.get(ch)-1);
                }
        }
        return count;
    }
}
