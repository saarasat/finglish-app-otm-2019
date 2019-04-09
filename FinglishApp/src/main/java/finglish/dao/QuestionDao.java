
package finglish.dao;

import java.util.ArrayList;
import finglish.domain.Question;

public interface QuestionDao {
    
    Question create(Question question) throws Exception;
    
    ArrayList<Question> getAll();
    
   
    
}
