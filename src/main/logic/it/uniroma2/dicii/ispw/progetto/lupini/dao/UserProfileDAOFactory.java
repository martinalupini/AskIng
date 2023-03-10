package it.uniroma2.dicii.ispw.progetto.lupini.dao;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class UserProfileDAOFactory {

    private UserProfileDAOFactory(){}

    private static UserProfileDAOFactory instance = null;

    public static UserProfileDAOFactory getInstance() {
        if(instance ==null){
            instance = new UserProfileDAOFactory();
        }
        return instance;
    }

    public UserProfileDAO createUserDAO(){
        SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        float randomNumber = buffer.getFloat();

        if(randomNumber > 0.5) {
            return new UserProfileDAOJDBC();
        }else {
            return new UserProfileDAOCSV();
        }
    }

    public UserProfileDAO createUserDAOJDBC(){
        return new UserProfileDAOJDBC();
    }

    public UserProfileDAO createUserDAOCSV(){
        return new UserProfileDAOCSV();
    }
}
