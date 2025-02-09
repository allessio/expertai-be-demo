package ai.expert.assessment.test;

import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.security.Authenticator;
import ai.expert.nlapi.security.BasicAuthenticator;
import ai.expert.nlapi.security.Credential;
import ai.expert.nlapi.v1.API;
import ai.expert.nlapi.v1.Analyzer;
import ai.expert.nlapi.v1.AnalyzerConfig;
import ai.expert.nlapi.v1.message.ResponseDocument;

public class AnalisysTest {

    static StringBuilder sb = new StringBuilder();

    // Sample text to be analyzed
    static {
        sb.append("Michael Jordan was one of the best basketball players of all time.");
        sb.append("Scoring was Jordan's stand-out skill, but he still holds a defensive NBA record, with eight steals in a half.");  
    }

    public static String getSampleText() {
        return sb.toString();
    }
    
    //Method for setting the authentication credentials - set your credentials here.
    public static Authentication createAuthentication() throws Exception {
        Authenticator authenticator = new BasicAuthenticator(new Credential("USERNAME", "PWD"));
        return new Authentication(authenticator);
    }

    //Method for selecting the resource to be call by the API; as today, the API provides the standard context only, and  
    //five languages such as English, French, Spanish, German and Italian
    public static Analyzer createAnalyzer() throws Exception {
        return new Analyzer(AnalyzerConfig.builder()
                                          .withVersion(API.Versions.V1)
                                          .withContext(API.Contexts.STANDARD)
                                          .withLanguage(API.Languages.en)
                                          .withAuthentication(createAuthentication())
                                          .build());
    }

    public static void main(String[] args) {
        try {
            Analyzer analyzer = createAnalyzer();
            ResponseDocument response = null;
            
            // Disambiguation Analisys
            System.out.println("============== Disambiguation Analisys");
            response = analyzer.disambiguation(getSampleText());
            response.prettyPrint();

            // Relevants Analisys
            System.out.println("============== Relevants Analisys");
            response = analyzer.relevants(getSampleText());
            response.prettyPrint();

            // Entities Analisys
            System.out.println("============== Entities Analisys");
            response = analyzer.entities(getSampleText());
            response.prettyPrint();
            
            // Full Analisys
            System.out.println("============== Full Analisys");
            response = analyzer.analyze(getSampleText());
            response.prettyPrint();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}