package com.example.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Store> stores = new ArrayList<>();

    // 의미 있는 변경 메서드
    public void changeName(String name) {
        this.name = name;
    }

    // 양방향 편의 메서드
    public void addStore(Store store) {
        this.stores.add(store);
        store.setLocationInternal(this); // private 세터로 내부에서만 연결
    }

    // 내부 전용(양방향 세팅용)
    void removeStore(Store store) {
        this.stores.remove(store);
        store.setLocationInternal(null);
    }
}