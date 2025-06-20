package com.eagle.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Category {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long category_id;

    @Column(name = "category_name" ,unique = true)
    @NonNull
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategoryName;

}
