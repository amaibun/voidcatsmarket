package com.amaibun.voidcatsmarket.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Long customerId;

  @NotNull
  @Column(nullable = false)
  private String name;

  @Email
  @NotNull
  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "phone_number", unique = true)
  private String phoneNumber;

  @OneToMany(mappedBy = "customer", orphanRemoval = true)
  private List<Order> orders;
}
