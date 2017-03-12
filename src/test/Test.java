
package test;

import java.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.*;

public class Test {

    public void test1() {
        String text = "This World is an amazing place";
        text = "I like raining";
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation annotation = pipeline.process(text);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println(sentiment + "\t" + sentence);
        }
    }

    public void testUnwrapped() throws Exception
    {
        String text = "\"Hey you!\", John said.";

        String[] expectedSentences = { "0 10 \"Hey you!\"", "10 22 , John said." };
        String[] expectedTokens = { "0 1 `` \"", "1 4 Hey Hey", "5 8 you you", "8 9 ! !",
                "9 10 '' \"", "10 11 , ,", "12 16 John John", "17 21 said said", "21 22 . ." };

        List<String> sentences = new ArrayList<String>();
        List<String> tokens = new ArrayList<String>();

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = pipeline.process(text);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            sentences.add(String.format("%d %d %s",
                    sentence.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class),
                    sentence.get(CoreAnnotations.CharacterOffsetEndAnnotation.class),
                    sentence.get(CoreAnnotations.TextAnnotation.class)));
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                tokens.add(String.format("%d %d %s %s",
                        token.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class),
                        token.get(CoreAnnotations.CharacterOffsetEndAnnotation.class),
                        token.get(CoreAnnotations.TextAnnotation.class),
                        token.get(CoreAnnotations.OriginalTextAnnotation.class)));
            }
        }

//        System.out.println(AssertAnnotations.asCopyableString(sentences, true));
//        System.out.println(AssertAnnotations.asCopyableString(tokens, true));
//
//        assertEquals(asList(expectedSentences), sentences);
//        assertEquals(asList(expectedTokens), tokens);
    }

    public static final void main(String[] args) {
        Test test = new Test();
        test.test1();
//        try {
//            test.testUnwrapped();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}