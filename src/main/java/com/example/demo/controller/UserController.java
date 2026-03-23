package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users") // Déclare que toutes les routes de ce contrôleur commenceront par /users
public class UserController {

    private final UserRepository userRepository; // Référence au repository pour accéder à la base de données

    // Injection du repository via le constructeur pour permettre l'accès aux opérations CRUD
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour afficher la liste complète des utilisateurs
    @GetMapping
    public String displayUserList(Model model) {
        model.addAttribute("users", userRepository.findAll()); // Récupère tous les utilisateurs et les envoie à la vue
        return "index"; // Retourne la page index.html qui affiche la liste
    }

    // Méthode pour afficher le formulaire de création d'un nouvel utilisateur
    @GetMapping("/new")
    public String displayCreateForm(Model model) {
        model.addAttribute("user", new User()); // Prépare un objet vide pour le formulaire
        return "add-user"; // Affiche la page add-user.html
    }

    // Méthode pour enregistrer un nouvel utilisateur dans la base de données
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           Model model) {

        // Vérifie si des erreurs de validation existent (ex: champs obligatoires non remplis)
        if (result.hasErrors()) {
            return "add-user"; // Si erreurs, on reste sur le formulaire
        }

        userRepository.save(user); // Sauvegarde l'utilisateur dans la base de données
        return "redirect:/users"; // Redirige vers la liste des utilisateurs après ajout
    }

    // Méthode pour afficher le formulaire de modification d'un utilisateur existant
    @GetMapping("/edit/{id}")
    public String displayEditForm(@PathVariable Long id, Model model) {
        // Cherche l'utilisateur par son id, lance une exception si non trouvé
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'id : " + id));

        model.addAttribute("user", existingUser); // Prépare l'utilisateur existant pour le formulaire
        return "update-user"; // Affiche la page update-user.html
    }

    // Méthode pour mettre à jour les informations d'un utilisateur existant
    @PostMapping("/update/{id}")
    public String updateExistingUser(@PathVariable Long id,
                                     @Valid @ModelAttribute("user") User user,
                                     BindingResult result) {

        // Vérifie les erreurs de validation lors de la modification
        if (result.hasErrors()) {
            user.setId(id); // Garde l'id pour ne pas perdre la référence
            return "update-user"; // Retourne au formulaire si erreurs
        }

        user.setId(id); // Définit l'id de l'utilisateur pour la mise à jour
        userRepository.save(user); // Sauvegarde les nouvelles informations
        return "redirect:/users"; // Redirige vers la liste après mise à jour
    }

    // Méthode pour supprimer un utilisateur existant
    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable Long id) {
        // Cherche l'utilisateur à supprimer par son id, lance une exception si non trouvé
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'id : " + id));

        userRepository.delete(userToDelete); // Supprime l'utilisateur de la base de données
        return "redirect:/users"; // Redirige vers la liste après suppression
    }
}
