package cz.itnetwork.entity.repository;

import cz.itnetwork.constant.RoleType;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Page<PersonEntity> getAllByRole(RoleType role, Pageable page);

    @Query(value = "SELECT * from person WHERE role=:#{#role.name()} LIMIT :limit", nativeQuery = true)
    List<PersonEntity> getAllByRole(@Param("role") RoleType role, @Param("limit") int limit);

}