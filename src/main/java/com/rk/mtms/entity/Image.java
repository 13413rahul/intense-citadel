package com.rk.mtms.entity;

import java.io.Serializable;
        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;

import com.rk.mtms.enums.EntityType;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

        import javax.persistence.*;
@Entity
@Data
@Table(name="image")
@EntityListeners(AuditingEntityListener.class)
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
//    @Column(name="image_type")
//    private ImageType imageType;
    @Column(name="entity_id")
    private int entityId;
    @Column(name="entity_Type")
    private EntityType entityType;
    @Column(name="url")
    private String url;
    @Column(name="user_id")
    private Integer userId;

}