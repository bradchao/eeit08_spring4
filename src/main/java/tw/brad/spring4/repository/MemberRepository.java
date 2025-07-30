package tw.brad.spring4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring4.entity.Member;
import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByAccount(String account);
}
