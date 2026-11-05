package com.example.taskmanager.service; // Emplacement du fichier

// Importations des fichiers nécessaires du projet
import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.Model.Task;
import com.example.taskmanager.Repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service // ⚙️ Dit à Spring que cette classe contient la logique métier et doit être gérée comme un composant d'application
public class TaskService {

    // Lien vers le Repository pour pouvoir interagir avec PostgreSQL
    private final TaskRepository repository;

    // Constructeur : Permet à Spring d'injecter automatiquement le Repository au démarrage
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // 🔍 Récupérer toutes les tâches
    @Transactional(readOnly = true) // Optimise la performance en disant à la base de données qu'on va juste LIRE (pas de modification)
    public List<Task> findAll() {
        return repository.findAllByOrderByCreatedAtDesc(); // Appelle la méthode triée du Repository
    }

    // 🔍 Trouver une tâche par son identifiant unique (ID)
    @Transactional(readOnly = true) // Mode lecture seule
    public Task findById(Long id) {
        return repository.findById(id) // Cherche dans la base
                .orElseThrow(() -> new TaskNotFoundException(id)); // 🚨 Si l'id n'existe pas, déclenche notre erreur personnalisée 404
    }

    // ➕ Créer et enregistrer une nouvelle tâche
    @Transactional // Ouvre une transaction SQL : si un bug survient pendant l'enregistrement, tout s'annule proprement
    public Task create(TaskRequest request) {
        Task task = new Task(); // Crée un objet Tâche vide
        task.setTitle(request.getTitle()); // Lui donne le titre envoyé par l'utilisateur
        task.setDescription(request.getDescription()); // Lui donne la description envoyée
        return repository.save(task); // Enregistre la tâche dans PostgreSQL et la renvoie avec son ID généré
    }

    // 📝 Modifier une tâche existante
    @Transactional // Mode écriture active
    public Task update(Long id, TaskRequest request) {
        Task task = findById(id); // Étape 1 : Vérifie d'abord si la tâche existe (sinon déclenche la 404)
        task.setTitle(request.getTitle()); // Étape 2 : Applique le nouveau titre
        task.setDescription(request.getDescription()); // Étape 3 : Applique la nouvelle description
        return repository.save(task); // Étape 4 : Sauvegarde les modifications en base
    }

    //  Inverser le statut d'une tâche (Cocher / Décocher)
    @Transactional
    public Task toggle(Long id) {
        Task task = findById(id); // Trouve la tâche concernée
        task.setCompleted(!task.isCompleted()); // Inverse le booléen : si c'était vrai ça devient faux, et inversement
        return repository.save(task); // Sauvegarde le nouveau statut
    }

    // ❌ Supprimer définitivement une tâche
    @Transactional
    public void delete(Long id) {
        Task task = findById(id); // Vérifie si elle existe bien avant d'essayer de la supprimer
        repository.delete(task); // Efface la ligne correspondante dans PostgreSQL
    }
}
