package com.eagle.productservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategoryName;


    public Category() {
    }


    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


    public Category(Long category_id, String categoryName, Category parentCategoryName) {
        this.category_id = category_id;
        this.categoryName = categoryName;
        this.parentCategoryName = parentCategoryName;
    }



    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(Category parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategoryName=" + (parentCategoryName != null ? parentCategoryName.getCategory_id() : null) +
                '}';
    }
}
