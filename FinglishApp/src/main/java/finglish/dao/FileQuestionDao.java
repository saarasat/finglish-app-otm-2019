
package finglish.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import finglish.domain.Question;


public class FileQuestionDao implements QuestionDao {
    
    private ArrayList<Question> questions;
    private String file;
    
    public FileQuestionDao(String file) throws Exception {
        questions = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] pieces = reader.nextLine().split(";");
                int id = Integer.parseInt(pieces[0]);
                Question q = new Question(id, pieces[1], pieces[2], pieces[3], pieces[4], pieces[5], pieces[6]);
                questions.add(q);
                q.setId(id);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
            System.out.println("No file");
        }
    }
    
    
    
    /**
    * Generates a serial Id for the question.
    * 
    * @return an Integer value to be used for the question id.
    */
    
    private int generateId() {
        return questions.size() + 1;
    }
    
    /**
    * Gets all the game-data stored.
    * 
    * @return an arraylist of game-data.
    */
    
    @Override
    public ArrayList<Question> getAll() {
        return questions;
    }
    
 
    /**
    * Adds a Question for permanent keeping.
    * Overrides the method of QuestionDao for create.
    * 
    * @param question takes in an object of the Question-class, without the id, which is generated in this method.
    * 
    * @return a successfully created and saved new question.
    */   
    
    @Override
    public Question create(Question question) throws Exception {
        question.setId(generateId());
        questions.add(question);
        saveNewQuestion();
        return question;
    }
    
    /**
    * Saves the new question for permanent keeping.
    * Uses a filewriter to write the question in the question-file. Private method for this class.
    * 
    */
      
    private void saveNewQuestion() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Question question : questions) {
                writer.write(question.getId() 
                        + ";" + question.getQuestion() 
                        + ";" + question.getOption() 
                        + ";" + question.getOption() 
                        + ";" + question.getOption() 
                        + ";" + question.getOption() 
                        + ";" + question.getCorrectAnswer() + "\n");
                System.out.println(question.getQuestion());
            }
        }
    }   
    
}
