package dev.gabrielmoreira.ResumoAI.Repository;

import dev.gabrielmoreira.ResumoAI.Model.Bookitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookItemRepository extends JpaRepository<Bookitem, Long> {
}
