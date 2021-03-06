
package finglish.dao;

import finglish.domain.Game;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class FileGameDao implements GameDao {
    
    private ArrayList<Game> games;
    private String file;
    private Random random;
    
    public FileGameDao(String file) throws Exception {
        games = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] pieces = reader.nextLine().split(";");
                int id = Integer.parseInt(pieces[0]);
                Game g = new Game(Integer.valueOf(pieces[1]), Integer.valueOf(pieces[2]), Integer.valueOf(pieces[3]));
                games.add(g);
                g.setId(id);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
            System.out.println("No file");
        }
    }
    
    /**
    * Generates a random Id for the game.
    * 
    * @return an Integer value to be used as a game id.
    */
    
    private int generateId() {
        this.random = new Random();
        return random.nextInt(999999999);
    }

    /**
    * Gets all the game-data stored.
    * 
    * @return an arraylist of game-data.
    */
    
    @Override
    public ArrayList<Game> getAll() {
        return games;
    }
    
    /**
    * Adds a game for permanent keeping.
    * Overrides the method of GameDao for create.
    * 
    * @param game takes in an object of the Game-class, without the id, which is generated in this method.
    * 
    * @return a successfully created and saved new game.
    */
    
    @Override
    public Game create(Game game) throws Exception {
        game.setId(generateId());
        games.add(game);
        saveNewGame();
        return game;
    }
    
    @Override
    public boolean findById(int accountId) {
        for (Game game : games) {
            if (game.getAccountId() == accountId) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
    * Saves the game question for permanent keeping.
    * Uses a filewriter to write the game in the games-file. Private method for this class.
    * Saves the information of how many questions are correctly answered, account_id and the game's id.
    */
    
    private void saveNewGame() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Game game : games) {
                writer.write(game.getId()
                    + ";" + game.getAccountId() 
                    + ";" + game.getAmountOfCorrectAnswers() 
                    + ";" + game.getQuestionCounter() + "\n");
            }
        }
    }
    
}
