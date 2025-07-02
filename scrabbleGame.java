//Diego Regalado
// CPSC-39
// 7-1-2025
// fixed type on assigment- collins file

//imports 

import java.io.*; //input
import java.util.*; //arrayLists and other utiltiies

// define scrabble class and arrayList
public class scrabbleGame{

    private static ArrayList<Words> wordsList = new ArrayList<>();

    //main function
    public static void main(String[] args){
        loadWordsFromFile("CollinsScrabbleWords_2019.txt");
        Collections.sort(wordsList);

        // generation
        String pool = buildLetterPool();                          // <-- NEW
        char[] letters = generateRandomLettersFromPool(4, pool);  // <-- CHANGED
        System.out.println("Your letters are: " + Arrays.toString(letters));

        // input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word using those letters: ");
        String userInput = scanner.nextLine().toLowerCase();

        // validation
        if (!isWordFromLetters(userInput, letters)) {
            System.out.println("Invalid word: you used letters that were not given.");
            return;
        }

        // binary search 
        boolean valid = binarySearch(userInput);
        if (valid) {
            System.out.println("valid word");
        } else {
            System.out.println("= not a valid Scrabble word.");
        }
    }

    private static void loadWordsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordsList.add(new Words(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Error reading word list: " + e.getMessage());
        }
    }

    // NEW METHOD: builds a string containing all letters in all words
    private static String buildLetterPool() {
        StringBuilder pool = new StringBuilder();
        for (Words w : wordsList) {
            pool.append(w.getText());
        }
        return pool.toString();
    }

    // NEW METHOD: picks random letters from the pool string
    private static char[] generateRandomLettersFromPool(int n, String pool) {
        Random rand = new Random();
        char[] letters = new char[n];
        for (int i = 0; i < n; i++) {
            letters[i] = pool.charAt(rand.nextInt(pool.length()));
        }
        return letters;
    }

    private static boolean isWordFromLetters(String word, char[] letters) {
        List<Character> available = new ArrayList<>();
        for (char c : letters) available.add(c);

        for (char c : word.toCharArray()) {
            if (!available.remove((Character) c)) {
                return false;
            }
        }
        return true;
    }

    // Binary search 
    private static boolean binarySearch(String word) {
        int low = 0;
        int high = wordsList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Words midWord = wordsList.get(mid);
            int cmp = word.compareTo(midWord.getText());

            if (cmp == 0) return true;
            else if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }
}
