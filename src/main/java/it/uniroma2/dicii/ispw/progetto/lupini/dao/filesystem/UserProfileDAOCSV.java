package it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Moderator;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Role;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class UserProfileDAOCSV implements UserProfileDAO {

    private static final String CSV_FILE_NAME = "src/main/resources/it/uniroma2/dicii/ispw/progetto/lupini/view/UserData.csv";

    @Override
    public UserProfile retrieveUserFromUsernameAndPassword(String username, String password) throws Exception {
        File fd = new File(CSV_FILE_NAME);
        BufferedReader reader = new BufferedReader( new FileReader(fd));
        String line;
        String[] column;
        UserProfile user;
        Role role;

        /* The organisation of the csv file is username,password,email,role,points,badBehaviour*/
        while((line = reader.readLine()) != null){
            column = line.split(",");
            if(column[0].equals(username) && column[1].equals(password)){
                if(column[3].equals("regular")){
                    role = new RegularUser(Integer.parseInt(column[4]), Integer.parseInt(column[5]));
                }else{
                    role = new Moderator();
                }
                user = new UserProfile(username, column[2], role );

                return user;

            }
        }

        //if all the file is read and a match between username and password is not found then the username or password is not correct
        throw new ItemNotFound("Item not found");

    }
    @Override
    public UserProfile retrieveUserFromUsername(String username) throws Exception {
        File fd = new File(CSV_FILE_NAME);
        BufferedReader reader = new BufferedReader( new FileReader(fd));
        String line;
        String[] column;
        UserProfile user;
        Role role;

        /* The organisation of the csv file is username,password,email,role,points,badBehaviour*/
        while((line = reader.readLine()) != null){
            column = line.split(",");
            if(column[0].equals(username)){
                if(column[3].equals("regular")){
                    role = new RegularUser(Integer.parseInt(column[4]), Integer.parseInt(column[5]));
                }else{
                    role = new Moderator();
                }
                user = new UserProfile(username, column[2], role );

                return user;

            }
        }

        //if all the file is read and a match between username and password is not found then the username or password is not correct
        throw new ItemNotFound("Item not found");

    }

}
