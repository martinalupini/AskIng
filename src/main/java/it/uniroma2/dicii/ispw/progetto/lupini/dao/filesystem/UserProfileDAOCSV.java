package it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Moderator;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Role;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

import java.io.*;

public class UserProfileDAOCSV implements UserProfileDAO {

    private static final String CSV_FILE_NAME = "src/main/resources/it/uniroma2/dicii/ispw/progetto/lupini/view/UserData.csv";

    @Override
    public UserProfile retrieveUserFromUsernameAndPassword(String username, String password) throws ItemNotFound, DBNotAvailable {
        File fd = new File(CSV_FILE_NAME);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fd));
            String line;
            String[] column;
            UserProfile user;
            Role role;

            /* The organisation of the csv file is username,password,email,role,points,badBehaviour*/
            while ((line = reader.readLine()) != null) {

                column = line.split(",");
                if (column[0].equals(username) && column[1].equals(password)) {
                    if (column[3].equals("regular")) {
                        role = new RegularUser(Integer.parseInt(column[4]), Integer.parseInt(column[5]));
                    } else {
                        role = new Moderator();
                    }
                    user = new UserProfile(username, column[2], role);

                    return user;

                }
            }

            //if all the file is read and a match between username and password is not found then the username or password is not correct
            throw new ItemNotFound("Item not found");
        }catch (IOException e) {
                throw new DBNotAvailable("Error in opening or reading file");
        }

    }

    @Override
    public UserProfile retrieveUserFromUsername(String username) throws ItemNotFound, DBNotAvailable {
        File fd = new File(CSV_FILE_NAME);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fd));

            String line;
            String[] column;
            UserProfile user;
            Role role;

        /* The organisation of the csv file is username,password,email,role,points,badBehaviour*/
        while ((line = reader.readLine()) != null) {

            column = line.split(",");
            if (column[0].equals(username)) {
                if (column[3].equals("regular")) {
                    role = new RegularUser(Integer.parseInt(column[4]), Integer.parseInt(column[5]));
                } else {
                    role = new Moderator();
                }
                user = new UserProfile(username, column[2], role);

                return user;

            }
        }

        //if all the file is read and a match between username and password is not found then the username or password is not correct
        throw new ItemNotFound("Item not found");

        } catch (IOException e) {
            throw new DBNotAvailable("Error in opening or reading file");
        }

    }

    @Override
    public void changeRoleOfUser(String username) throws ImpossibleToUpdate {
        File fd = new File(CSV_FILE_NAME);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fd));


            String line;
            String[] column;
            String newLine = "";

            /* The organisation of the csv file is username,password,email,role,points,badBehaviour*/
            while ((line = reader.readLine()) != null) {

                column = line.split(",");

                if (column[0].equals(username)) {
                    int i;
                    column[3] = "moderator";
                    column[4] = "";
                    column[5] = "";

                    for (i = 0; i < 5; i++) {
                        newLine = newLine + column[i] + ",";
                    }
                    newLine = newLine + column[5] + "\n";

                } else {
                    newLine = newLine + line + "\n";
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fd));
            writer.write(newLine);
            reader.close();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
