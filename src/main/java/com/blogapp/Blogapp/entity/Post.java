package com.blogapp.Blogapp.entity;


import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(
        name = "posts" , uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
      )
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "description" , nullable = false)
    private String description;

    @Lob
    @Column(name = "content" , nullable = false)
    private String content;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true)
    Set<Comment> comments = new HashSet<>();
}
