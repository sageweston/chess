package dataaccess;

import model.AuthData;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO{

    private static Collection<AuthData> auths = new HashSet<AuthData>();

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        for (AuthData auth : auths) {
            if (auth.authToken().equals(authToken)) {
                return auth;
            }
        }
        throw new DataAccessException("Authentication does not exist");
    }

    @Override
    public String createAuth(String username) throws DataAccessException {
        AuthData newAuth = new AuthData(UUID.randomUUID().toString(), username);
        for (AuthData auth : auths) {
            if (auth.username().equals(username)) {
                throw new DataAccessException("User already authenticated");
            }
        }
        auths.add(newAuth);
        return newAuth.authToken();
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        for (AuthData auth : auths) {
            if (auth.authToken().equals(authToken)) {
                auths.remove(auth);
                return;
            }
        }
        throw new DataAccessException("Authentication does not exist");
    }

    @Override
    public void deleteAuths() {
        auths.clear();
    }
}
