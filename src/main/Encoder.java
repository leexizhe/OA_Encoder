import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Encoder {

    private static final String decryption = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    private static Map<Character, Integer> hashMap() {
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < decryption.length(); i++) {
            hashMap.put(decryption.charAt(i), i);
        }
        return hashMap;
    }

    public static String encode(String encodedText) {
        Map<Character, Integer> hashMap = hashMap();
        char charFirst = encodedText.charAt(0);
        if (!hashMap.containsKey(charFirst)) {
            return encodedText;
        } else {
            StringBuilder text = new StringBuilder(String.valueOf(charFirst));
            int shift = hashMap.get(charFirst);
            int decryptionLength = decryption.length();
            for (int i = 1; i < encodedText.length(); i++) {
                char character = encodedText.charAt(i);
                if (Objects.isNull(hashMap.get(character))) {
                    text.append(character);
                    continue;
                }
                int index = hashMap.get(character) - shift;
                if (index < 0) {
                    text.append(decryption.charAt(index + decryptionLength));
                } else {
                    text.append(decryption.charAt(index));

                }
            }
            return text.toString();
        }
    }

    public static String decode(String encodedText) {
        Map<Character, Integer> hashMap = hashMap();
        char charFirst = encodedText.charAt(0);
        if (!hashMap.containsKey(charFirst)) {
            return encodedText;
        } else {
            StringBuilder text = new StringBuilder(String.valueOf(charFirst));
            int shift = hashMap.get(charFirst);
            int decryptionLength = decryption.length();
            for (int i = 1; i < encodedText.length(); i++) {
                char character = encodedText.charAt(i);
                if (Objects.isNull(hashMap.get(character))) {
                    text.append(character);
                    continue;
                }
                int index = hashMap.get(character) + shift;
                if (index > decryptionLength) {
                    text.append(decryption.charAt(index - decryptionLength));
                } else {
                    text.append(decryption.charAt(index));

                }
            }
            return text.toString();
        }
    }

    public static void main(String[] args) {
        String text = "BHELLO WORLD]";
        String encoded = encode(text);
        System.out.println(encoded);

        String text1 = "$HELLO WORLD";
        String encoded1 = encode(text1);
        System.out.println(encoded1);

        String encodedText = "BGDKKN VNQKC";
        String decoded = decode(encodedText);
        System.out.println(decoded);
    }

}
