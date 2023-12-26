package com.example.diseaseapi.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity(name = "Disease")
@Table(name = "disease")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Disease {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "definition", length = Length.LONG)
  private String definition;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
  private List<Reason> reasons;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
  private List<Symptom> symptoms;

  @Column(name = "therapy", length = Length.LONG)
  private String therapy;

  @Column(name = "classification", length = Length.LONG)
  private String classification;

  @Column(name = "danger", length = Length.LONG)
  private String danger;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
  private List<RiskItem> riskGroup;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
  private List<DiagnosticItem> diagnostic;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
  private List<PreventionItem> preventions;

  @ManyToOne()
  @JoinColumn(name = "doctorSpeciality_id", nullable = false)
  private DoctorSpeciality doctorSpeciality;

  @ManyToOne()
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @Column(name = "image")
  private byte[] image;
}
