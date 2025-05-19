package com.exercicios.service;

import com.exercicios.model.User;
import com.exercicios.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar usuários do JSON", e);
        }
    }

    public Optional<User> findById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + id, e);
        }
    }

    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar/atualizar usuário", e);
        }
    }

    public void deleteById(Long id) {
        try {
            Optional<User> user = findById(id);
            if(user.isPresent()){
                userRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Usuário não encontrado");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao excluir usuário por ID: " + id, e);
        }
    }
}
