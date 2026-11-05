package com.example.taskmanager.controller; // Emplacement du fichier

// Importations des outils nécessaires
import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.Model.Task;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // ⚙️ Transforme cette classe en contrôleur API capable de recevoir et renvoyer du texte (JSON)
@RequestMapping("/api/tasks") // 📦 Toutes les adresses de ce contrôleur commenceront par "http://localhost:9002/api/tasks"
public class TaskController {

    // Connexion avec le fichier Service (C'est lui qui contient la logique de calcul)
    private final TaskService service;

    // Constructeur : permet à Spring d'injecter automatiquement le "TaskService" ici au démarrage
    public TaskController(TaskService service) {
        this.service = service;
    }

    // 🔍 GET http://localhost:9002/api/tasks
    @GetMapping // Dit à Spring d'activer cette méthode quand on demande à "LIRE" la liste des tâches
    public List<Task> getAll() {
        return service.findAll(); // Demande au service de chercher toutes les tâches et les renvoie
    }

    // 🔍 GET http://localhost:9002/api/tasks/5 (par exemple)
    @GetMapping("/{id}") // Le "{id}" est une variable (le numéro de la tâche qu'on veut voir)
    public Task getOne(@PathVariable Long id) { // @PathVariable associe le numéro de l'URL à la variable "id"
        return service.findById(id); // Demande au service de chercher cette tâche précise
    }

    // ➕ POST http://localhost:9002/api/tasks
    @PostMapping // Dit à Spring qu'on veut "CRÉER/AJOUTER" quelque chose
    @ResponseStatus(HttpStatus.CREATED) // Renvoie le code de succès HTTP 201 (Créé avec succès) au lieu de 200
    public Task create(@Valid @RequestBody TaskRequest request) { 
        // @Valid : Force la vérification des règles du titre (pas vide, bonne taille)
        // @RequestBody : Récupère les données envoyées par l'utilisateur (le titre et la description)
        return service.create(request); // Demande au service d'enregistrer la nouvelle tâche
    }

    // 📝 PUT http://localhost:9002/api/tasks/5
    @PutMapping("/{id}") // Utilisé pour "MODIFIER/METTRE À JOUR" entièrement une tâche existante
    public Task update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        return service.update(id, request); // Demande au service d'appliquer les changements sur la tâche "id"
    }

    //  PATCH http://localhost:9002/api/tasks/5/toggle
    @PatchMapping("/{id}/toggle") // Utilisé pour une modification partielle (ici, cocher/décocher la case "Terminé")
    public Task toggle(@PathVariable Long id) {
        return service.toggle(id); // Demande au service d'inverser le statut de la tâche (Terminé <-> En cours)
    }

    // ❌ DELETE http://localhost:9002/api/tasks/5
    @DeleteMapping("/{id}") // Utilisé pour "SUPPRIMER" une tâche
    @ResponseStatus(HttpStatus.NO_CONTENT) // Renvoie le code HTTP 204 (Succès, mais il n'y a plus rien à afficher)
    public void delete(@PathVariable Long id) {
        service.delete(id); // Demande au service de supprimer définitivement la tâche de la base de données
    }
}
