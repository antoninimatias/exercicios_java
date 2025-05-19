package com.exercicios.repository;

import com.exercicios.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final String FILE_PATH = "src/main/resources/data/dados.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<User> loadUsers() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, new TypeReference<List<User>>() {
        });
    }


    private void saveUsers(List<User> users) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), users);
    }

    public List<User> findAll() throws IOException {
        return loadUsers();
    }

    public Optional<User> findById(Long id) throws IOException {
        return loadUsers().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User save(User user) throws IOException {
        List<User> users = loadUsers();

        if (user.getId() == null) {
            user.setId((long) (users.size() + 1));
            users.add(user);
            saveUsers(users);
        } else {
            Optional<User> existingUserOpt = users.stream().filter(u -> user.getId().equals(u.getId())).findFirst();

            if (existingUserOpt.isPresent()) {
                User existingUser = existingUserOpt.get();
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                saveUsers(users);

                return existingUser;
            } else {
                throw new EntityNotFoundException("Usuário com ID " + user.getId() + " não encontrado.");
            }
        }

        return user;
    }

    public boolean deleteById(Long id) throws IOException {
        List<User> users = loadUsers();
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (removed) {
            saveUsers(users);
        } else {
            throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
        }
        return true;
    }
}


