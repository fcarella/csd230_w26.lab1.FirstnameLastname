package csd230.lab1.repositories;

import csd230.lab1.entities.AbstractCosmeticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CosmeticsEntityRepository<T extends AbstractCosmeticsEntity> extends JpaRepository<T, Long> {
}