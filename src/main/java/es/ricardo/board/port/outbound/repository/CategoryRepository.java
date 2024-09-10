package es.ricardo.board.port.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ricardo.board.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
