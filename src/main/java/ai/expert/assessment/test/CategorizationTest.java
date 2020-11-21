package ai.expert.assessment.test;

import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.security.Authenticator;
import ai.expert.nlapi.security.BasicAuthenticator;
import ai.expert.nlapi.security.Credential;
import ai.expert.nlapi.v1.API;
import ai.expert.nlapi.v1.Categorizer;
import ai.expert.nlapi.v1.CategorizerConfig;
import ai.expert.nlapi.v1.message.ResponseDocument;

public class CategorizationTest {

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
        Authenticator authenticator = new BasicAuthenticator(new Credential("PUT HERE YOUR USERNAME", " PUT HERE YOUR PASSWORD"));
        return new Authentication(authenticator);
    }
    
    //Method for selecting the resource to be call by the API; as today, the API provides the IPTC classifier only, and 
    //five languages such as English, French, Spanish, German and Italian
    public static Categorizer createCategorizer() throws Exception {
        return new Categorizer(CategorizerConfig.builder()
                                                .withVersion(API.Versions.V1)
                                                .withTaxonomy(API.Taxonomies.IPTC)
                                                .withLanguage(API.Languages.en)
                                                .withAuthentication(createAuthentication())
                                                .build());
    }

    public static void main(String[] args) {
        try {
            Categorizer categorizer = createCategorizer();
            
            //Perform the IPTC classification and store it into a Response Object
            ResponseDocument categorization = categorizer.categorize(getSampleText());
            categorization.prettyPrint();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
