import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


import org.apache.commons.lang3.StringUtils;

class Main {
    public static void main(String[] args) throws IOException {
        String pathToTxt = Paths.get(System.getProperty("user.dir"),"Data.txt").toString();

        List<String> data = readFile(pathToTxt);
        HashSet<String> vowelWords = new HashSet<>();

        for (String word : data) {
            List<String> filtered = filterSymbols(Arrays.asList(word.split("")));
            Boolean ifVowels = checkSymbolsInWord(filtered);
            if (ifVowels) {
                String vowelsWord = joinSymbols(filtered, "");
                vowelWords.add(vowelsWord);
            }
        }

        System.out.println("Words with only vowels:\n");
        System.out.println(vowelWords);
    }

    private static List<String> filterSymbols(List<String> values) {
        return values.stream().filter(s -> !StringUtils.containsAny(s,'.', '(', ')', ',', '-', ';', ' ', '\n', '\t')).collect(Collectors.toList());
    }

    private static String joinSymbols(List<String> values, String delimiter) {
        return values.stream().collect(Collectors.joining(delimiter));
    }

//    private static final String[] vowels= new String[]{"а", "е", "и", "і", "о", "у", "я", "ю", "є", "ї"};

    private static final String[] vowels= new String[]{"a", "e", "i", "o", "u","y","Y", "A", "E", "I", "O", "U"};

    private static Boolean ifVowel(String letter) {
       for (int i = 0; i < vowels.length; i++) {
            if (letter.equals(vowels[i])){
                return true;
            }
        }
        return false;
    }
    private static Boolean checkSymbolsInWord(List<String> symbols) {
        if (symbols.isEmpty()){
            return false;
        }
        for (int i = 0; i < symbols.size(); i++) {
           if (!ifVowel(symbols.get(i))){
                return false;
           }
        }
        return true;
    }

    private static List<String> readFile(String path) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        try {
            line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        return Arrays.asList(sb.toString().split(" "));
    }
}
