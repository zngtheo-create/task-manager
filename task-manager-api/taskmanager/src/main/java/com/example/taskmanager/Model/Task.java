package com.example.taskmanager.Model; // Emplacement du fichier dans vos dossiers

// Importations des outils Jakarta Persistence pour configurer la base de données
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity // ⚙️ Indique à JPA/Hibernate que cette classe Java correspond à une table en base de données
@Table(name = "tasks") // 📦 Force le nom de la table SQL à s'appeler "tasks" dans PostgreSQL
@Getter // ⚙️ Extension Lombok : Génère automatiquement tous les "getters" (ex: getId(), getTitle()...)
@Setter // ⚙️ Extension Lombok : Génère automatiquement tous les "setters" (ex: setTitle(), setCompleted()...)
@NoArgsConstructor // ⚙️ Extension Lombok : Crée un constructeur vide sans paramètres, obligatoire pour JPA
public class Task {

    @Id // 🔑 Déclare cette variable comme la clé primaire unique de votre table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Dit à PostgreSQL d'incrémenter le numéro tout seul (1, 2, 3...)
    private Long id;

    @Column(nullable = false, length = 100) // 📝 Configuration SQL : Ce champ ne peut pas être vide (NOT NULL) et fait 100 caractères max
    private String title;

    @Column(length = 500) // 📝 Configuration SQL : La description peut être vide mais fait 500 caractères maximum
    private String description;

    @Column(nullable = false) // 📝 Configuration SQL : Ce champ ne peut pas être nul
    private boolean completed = false; // Par défaut, une nouvelle tâche créée n'est pas terminée (false)

    @Column(name = "created_at", nullable = false, updatable = false) 
    // name = "created_at" -> Renomme la colonne avec un "_" en SQL pour suivre les bonnes pratiques
    // updatable = false -> Interdit la modification de la date après sa création
    private LocalDateTime createdAt;

    @PrePersist // ⏳ Déclencheur automatique : S'exécute juste AVANT d'enregistrer la ligne dans PostgreSQL
    void onCreate() {
        this.createdAt = LocalDateTime.now(); // Enregistre automatiquement la date et l'heure exacte de création sur votre PC
    }
}
