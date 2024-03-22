package com.ping23.scratch.util;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

public class Lorem {

    private static final Random RANDOM = new Random();
    private static final String[] LOREM_WORDS = getLoremWords();

    /**
     * generate random lorem sentence
     * @return
     */
    public static String generateRandomLoremSentence()
    {
        final int numberOfWords = 5 + RANDOM.nextInt(10);
        StringBuilder sentence = new StringBuilder();
        for(int wordIndex = 1; wordIndex <= numberOfWords; wordIndex++)
        {
            final int loremIndex = RANDOM.nextInt(LOREM_WORDS.length);
            String word = LOREM_WORDS[loremIndex];
            if (wordIndex == 1) {
                word = StringUtils.capitalize(word);
            }
            sentence.append(word);
            if (wordIndex == numberOfWords) {
                sentence.append(RANDOM.nextInt() % 4 == 0 ? "?" : ".");
            } else {
                sentence.append(" ");
            }
        }
        return sentence.toString();
    }

    public static String[] getLoremWords() {
        final String loremSource1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        final String loremSource2 = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
        final String loremSource3 = "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.";
        String loremSourceAll =  loremSource1 + " " + loremSource2 + " " + loremSource3;
        loremSourceAll = loremSourceAll.toLowerCase().replaceAll(",", "").replaceAll("\\.", "").replaceAll("\\?", "");
        return loremSourceAll.split(" ");
    }

    public static void main(String[] args) {
        for (int index = 1; index <= 10; ++index) {
            final String sentence = generateRandomLoremSentence();
            System.out.println(sentence);
        }
    }

}
