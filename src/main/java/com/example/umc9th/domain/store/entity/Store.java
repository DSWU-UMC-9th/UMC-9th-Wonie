package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "manager_number", nullable = false, length = 20)
    private String managerNumber;

    @Column(name = "detail_address", nullable = false, length = 100)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mission> missions = new ArrayList<>();

    public void changeName(String name) { this.name = name; }
    public void changeManagerNumber(String managerNumber) { this.managerNumber = managerNumber; }
    public void changeDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }

    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    void setLocationInternal(Location location) {
        this.location = location;
    }
}
