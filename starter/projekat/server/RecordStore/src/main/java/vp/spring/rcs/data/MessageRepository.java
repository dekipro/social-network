package vp.spring.rcs.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import vp.spring.rcs.model.Message;

 
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{


}
