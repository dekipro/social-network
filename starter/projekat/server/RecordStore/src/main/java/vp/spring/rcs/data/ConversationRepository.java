package vp.spring.rcs.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vp.spring.rcs.model.Conversation;
import vp.spring.rcs.model.User;


public interface ConversationRepository extends JpaRepository<Conversation, Long>{
	Conversation findByFirstUserAndSecondUser(User firstUser, User secondUser);
}
