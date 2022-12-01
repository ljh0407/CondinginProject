package codingin.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RereplyRepository extends JpaRepository<RereplyEntity , Integer> {
}
