
package finglish.dao;

import finglish.domain.Question;

import java.util.ArrayList;

public interface QuestionDao {
    
    Question create(Question question) throws Exception;
    ArrayList<Question> getAll();

}
