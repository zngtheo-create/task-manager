package com.example.taskmanager.exception; // Emplacement du fichier dans les dossiers

// Importations des outils Spring et Java pour gérer les réponses et les tableaux de données
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

// ⚙️ Intercepte automatiquement toutes les erreurs/exceptions déclenchées dans vos contrôleurs
@RestControllerAdvice 
public class GlobalExceptionHandler {

    // 🛑 Cas 1 : Gestion de l'erreur quand une tâche n'existe pas dans la base de données
    @ExceptionHandler(TaskNotFoundException.class) // Dit à Spring d'activer cette méthode si cette erreur précise survient
    public ResponseEntity<Map<String, Object>> handleNotFound(TaskNotFoundException ex) {
        // Crée une boîte (Map) pour stocker les informations de l'erreur
        Map<String, Object> body = new HashMap<>();
        body.put("status", 404); // Ajoute le code d'erreur 404 (Introuvable)
        body.put("message", ex.getMessage()); // Récupère le message texte défini dans votre classe TaskNotFoundException
        
        // Renvoie la boîte avec le statut HTTP 404 officiel
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // 🛑 Cas 2 : Gestion de l'erreur si l'utilisateur envoie des données invalides (ex: titre vide ou trop court)
    @ExceptionHandler(MethodArgumentNotValidException.class) // Se déclenche automatiquement si le @Valid de votre DTO échoue
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        // Crée un tableau associatif pour lister chaque champ invalide associé à son message d'erreur
        Map<String, String> errors = new HashMap<>();
        
        // Parcourt la liste de toutes les erreurs détectées par Spring lors de la validation
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage())); // Stocke par exemple : {"title": "Le titre est obligatoire"}

        // Crée la boîte principale de la réponse finale
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400); // Ajoute le code d'erreur 400 (Mauvaise requête)
        body.put("message", "Données invalides"); // Message général
        body.put("errors", errors); // Ajoute la liste détaillée des champs qui bloquent
        
        // Renvoie le tout avec un statut HTTP 400 Bad Request
        return ResponseEntity.badRequest().body(body);
    }
}
