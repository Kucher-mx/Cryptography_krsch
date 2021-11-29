package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class Encrypt {
    final static ArrayList<Character> alph = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    public static String padRight(String s, int n) {
        String res = "";
        if (s.length() < n) {
            int i = 0;
            while (res.length() != n) {
                res += s.charAt(i);
                i = s.length() - 1 >= i + 1 ? i + 1 : 0;
            }
        } else {
            res = s.substring(0, n);
        }
        return res;
    }

    public static int findIndex(char c) {
        for (int i = 0; i < alph.size(); i++) {
            if (alph.get(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public String encrypt(String EWord, String key) {
        String result = "";
        String paddedKey = padRight(key, EWord.length());
        int i = 0;
        char[] letters = EWord.toCharArray();
        for (char letter : letters) {
            int idx = (findIndex(letter) + findIndex(paddedKey.charAt(i))) % alph.size();
            result += alph.get(idx);
            i++;
        }
        return result;
    }

    public String dencrypt(String EWord, String key) {
        String result = "";
        String paddedKey = padRight(key, EWord.length());
        int i = 0;
        char[] letters = EWord.toCharArray();
        for (char letter : letters) {
            int idx = (findIndex(letter) + alph.size() - findIndex(paddedKey.charAt(i))) % alph.size();
            result += alph.get(idx);
            i++;
        }
        return result;
    }
}
