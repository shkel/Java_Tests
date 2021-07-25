package dal.interfaces;

import dal.entities.IsinHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<IsinHistory, Long> {


}
