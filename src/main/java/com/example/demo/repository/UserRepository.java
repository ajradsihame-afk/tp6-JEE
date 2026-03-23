package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indique que cette interface est un composant Spring de type Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository fournit automatiquement :
    // - findAll() pour récupérer tous les utilisateurs
    // - findById(Long id) pour récupérer un utilisateur par son identifiant
    // - save(User user) pour créer ou mettre à jour un utilisateur
    // - delete(User user) pour supprimer un utilisateur
    // Aucun code supplémentaire n'est nécessaire pour ces opérations CRUD de base
}