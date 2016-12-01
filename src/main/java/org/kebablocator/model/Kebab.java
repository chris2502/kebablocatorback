package org.kebablocator.model;



import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by fchoudhry on 04/10/16.
 */
@Component
@Entity
@Table(name = "kebabs")
public class Kebab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private String nom;

    @NotNull
    private String adresse;

    @NotNull
    private String cp;

    @NotNull
    private String ville;

    public Kebab() {
    }

    public Kebab(String nom) {
        this.nom = nom;
    }

    public Kebab(Double latitude, Double longitude, String nom, String adresse, String cp, String ville) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.cp = cp;
        this.adresse = adresse;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() { return ville; }

    public void setVille(String ville) { this.ville = ville; }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
