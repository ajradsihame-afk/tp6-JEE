package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity // Indique que cette classe correspond à une table dans la base de données
public class User {

    // Identifiant unique de l'utilisateur, généré automatiquement par la base de données
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Nom de l'utilisateur : ne peut pas être vide, validation côté serveur avec un message personnalisé
    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    // Email de l'utilisateur : ne peut pas être vide, validation côté serveur avec un message personnalisé
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    // Constructeur par défaut nécessaire pour JPA afin d'instancier des objets User
    public User() {
    }

    // -------------------------
    // Getters et Setters
    // -------------------------

    // Récupérer l'identifiant de l'utilisateur
    public Long getId() {
        return id;
    }

    // Définir l'identifiant de l'utilisateur
    public void setId(Long id) {
        this.id = id;
    }

    // Récupérer le nom de l'utilisateur
    public String getName() {
        return name;
    }

    // Définir le nom de l'utilisateur
    public void setName(String name) {
        this.name = name;
    }

    // Récupérer l'email de l'utilisateur
    public String getEmail() {
        return email;
    }

    // Définir l'email de l'utilisateur
    public void setEmail(String email) {
        this.email = email;
    }
}