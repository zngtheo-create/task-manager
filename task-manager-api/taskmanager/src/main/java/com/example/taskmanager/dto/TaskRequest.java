package com.example.taskmanager.dto; // Emplacement du fichier

// Importations des outils de validation et des extensions
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter // ⚙️ Lombok génère automatiquement en arrière-plan les méthodes pour LIRE les variables (get...())
@Setter // ⚙️ Lombok génère automatiquement les méthodes pour MODIFIER les variables (set...())
public class TaskRequest {

    // 🛑 Règle : Le titre ne doit pas être vide ou rempli uniquement d'espaces
    @NotBlank(message = "Le titre est obligatoire")
    // 🛑 Règle : Le titre doit faire au minimum 3 lettres et au maximum 100 lettres
    @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
    private String title; // Variable stockant le titre reçu

    // 🛑 Règle : La description est optionnelle mais ne doit pas dépasser 500 lettres
    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
    private String description; // Variable stockant la description reçue
}
