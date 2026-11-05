package com.example.taskmanager; // Dossier racine de votre application

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
// ⚙️ Une annotation magique 3-en-1 : 
// 1. Active la configuration automatique de Spring (PostgreSQL, Tomcat, etc.)
// 2. Active le scan automatique de vos dossiers pour trouver vos @Service, @RestController, etc.
public class TaskmanagerApplication {

    // La méthode standard par laquelle tout programme Java commence son exécution
    public static void main(String[] args) {
        // Démarre officiellement le serveur Web Tomcat intégré et charge l'application Spring Boot
        SpringApplication.run(TaskmanagerApplication.class, args);
    }

}
