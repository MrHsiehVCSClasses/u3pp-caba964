package u3pp;

public class PalindromeTester {

    /**
     * Tests whether a string is a palindrome. Case insensitive. 
     * @param s  the string to test
     * @return true if the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        s = s.replace(" ", "");
        String reverse = "";
        for (int i = 0; i < s.length(); i++){
            reverse += s.charAt(i);
        }
        
        Boolean results = true;
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) != reverse.charAt(i)){
                results = false;
            } else {
                results = true;
            }
        }
        if(results) {
            return true;
        } else {
            return false;
        }
        }
    }