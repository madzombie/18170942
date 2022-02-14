package Configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lessons.react1.jsonEntity.User;
import com.lessons.react1.repositories.UserRepository;
import com.lessons.react1.services.UserService;

//spring security nin useri tanimasi icin

@Service
public class UserAuthService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User innoUser = userRepository.getUser(username);
		if (innoUser==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new ProjectUserDetails(innoUser);
	}

}
