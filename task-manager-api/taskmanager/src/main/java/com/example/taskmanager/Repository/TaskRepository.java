package com.example.taskmanager.Repository; // Emplacement du fichier

import com.example.taskmanager.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// ⚙️ JpaRepository<Task, Long> donne accès aux commandes magiques de base : save(), findById(), deleteById()...
// "Task" est l'entité concernée et "Long" est le type de sa clé primaire (son ID).
public interface TaskRepository extends JpaRepository<Task, Long> {

    // 🔍 Requête automatique générée par Spring grâce au nom de la méthode :
    // findAll = Récupère toutes les tâches
    // ByOrderByCreatedAtDesc = Trie-les par date de création, de la plus récente à la plus ancienne.
    List<Task> findAllByOrderByCreatedAtDesc();
}
