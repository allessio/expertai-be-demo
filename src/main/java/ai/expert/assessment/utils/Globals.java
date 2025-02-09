package ai.expert.assessment.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okhttp3.MediaType;

/**
 *
 * @author oserret
 */
public class Globals {

    public static final String VOID_STRING = "";
    public static final String TRUE = "1";
    public static final String FALSE = "0";
    public static final String LOCALHOST = "localhost";
    public static final int CERO = 0;
    public static final MediaType CONTENT = MediaType.get("application/json");
    public static final String ANALYSIS_TYPE_CAT = "_categorization";
    public static final String ANALYSIS_TYPE_EXT_DIS = "_extraction_disambiguation";
    public static final String ANALYSIS_TYPE_EXT_REL = "_extraction_relevants";
    public static final String ANALYSIS_TYPE_EXT_ENT = "_extraction_entities";
    public static final String ANALYSIS_TYPE_EXT_FULL = "_extraction_full";
    public static final String OUTPUT_EXTENSION = ".json";

    public static final int WS_CONTENT_LIMIT = 10000;
    
    public static final String PARAGRAPH_DELIMITER_FOR_WS = " ";
    public static final String SENTENCE_DELIMITER_FOR_WS = ". ";
    
    public static final String ENTITIES_ENDPOINT = "/entities";
    public static final String CONTENTS_ENDPOINT = "/contents";
    public static final String CONTENT_ANALYSIS_ENDPOINT = "/analysis";
    public static final String KNOWLEDGE_ENDPOINT = "/knowledge";
    
    public static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
}
