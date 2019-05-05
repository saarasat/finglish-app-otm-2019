
package finglishapp.domain;

import finglish.dao.QuestionDao;
import finglish.domain.Question;
import java.util.ArrayList;


public class FakeQuestionDao implements QuestionDao {
    
    ArrayList<Question> allQuestions;

    public FakeQuestionDao() {
        allQuestions = new ArrayList<>();
    }   
   
    @Override
    public ArrayList<Question> getAll() {
        return allQuestions;
    }
    
    @Override
    public Question create(Question question) {
        question.setId(allQuestions.size()+1);
        allQuestions.add(question);
        return question;
    }  
    
}
