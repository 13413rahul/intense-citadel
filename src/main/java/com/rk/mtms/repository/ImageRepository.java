package com.rk.mtms.repository;


import com.rk.mtms.entity.Image;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Component;
        import org.springframework.transaction.annotation.Transactional;


@Component
public interface ImageRepository extends CrudRepository<Image, Long> {
    @Modifying
    @Query(value="insert into Image(entity_id,entity_Type,url,image_type,user_id) VALUES(1,'student',:url,1,:userId)",nativeQuery = true)
    @Transactional
    void insertIntoImageTable(@Param("url") String url,@Param("userId") String userId);

    Image findByUserId(Integer requestedUserId);

}
