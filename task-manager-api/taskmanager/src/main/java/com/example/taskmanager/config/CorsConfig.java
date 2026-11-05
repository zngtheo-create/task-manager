package com.example.taskmanager.config; // Emplacement du fichier dans les dossiers

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ⚙️ Dit à Spring que ce fichier contient des configurations techniques pour l'application
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // 📦 Permet de configurer les règles CORS (Partage de ressources entre origines différentes)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // S'applique à toutes les URL qui commencent par "/api/"
                .allowedOrigins("http://localhost:4200") // Autorise UNIQUEMENT le Frontend Angular (port 4200) à appeler l'API
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE"); // Autorise ces actions spécifiques sur l'API
    }
}
