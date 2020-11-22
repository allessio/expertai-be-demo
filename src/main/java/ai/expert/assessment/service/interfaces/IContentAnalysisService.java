package ai.expert.assessment.service.interfaces;

import ai.expert.assessment.persistence.entity.ContentAnalysis;

public interface IContentAnalysisService extends ICrud<ContentAnalysis, Long> {
   
   public void analyzeCategoriesAllPersistedDocs();
   public void analyzeCategoriesPersistedDocs(Long docIDFrom, Long docIDTo);
   public void analyzeFullAllPersistedDocs();
   public void analyzeFullPersistedDocs(Long docIDFrom, Long docIDTo);
}
