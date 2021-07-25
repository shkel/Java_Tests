package dal.interfaces;

import dal.entities.IsinElvl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElvlRepository extends JpaRepository<IsinElvl, Long> {


}
