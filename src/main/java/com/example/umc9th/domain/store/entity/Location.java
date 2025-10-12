package com.example.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();
}