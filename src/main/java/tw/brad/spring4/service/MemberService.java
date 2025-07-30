package tw.brad.spring4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.brad.spring4.entity.Member;
import tw.brad.spring4.repository.MemberRepository;
import tw.brad.spring4.utils.BCrypt;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;
	
//	@Autowired
//	private BCryptPasswordEncoder passwdEncoder;
	
	public Member register(
		String account, String passwd, String name, MultipartFile iconFile)
		throws Exception {
		
		if (repository.findByAccount(account) != null) {
			throw new Exception();
		}
		
		String hashPasswd = BCrypt.hashpw(passwd, BCrypt.gensalt());
		byte[] icon = iconFile != null && !iconFile.isEmpty() ? iconFile.getBytes() : null;
		
		Member member = new Member();
		member.setAccount(account);
		member.setIcon(icon);
		member.setName(name);
		member.setPasswd(hashPasswd);
		
		return repository.save(member);
		
	}
	
	public Member login(String account, String passwd) {
		Member member = repository.findByAccount(account);
		if (member!=null && BCrypt.checkpw(passwd, member.getPasswd())) {
			return member;
		}
		return null;
	}
	
	
	
	
	
}
