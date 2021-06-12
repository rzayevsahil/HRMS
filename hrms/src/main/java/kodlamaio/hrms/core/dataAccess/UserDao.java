package kodlamaio.hrms.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.core.entities.User;

public interface UserDao extends JpaRepository<User,Integer> {
	User findUserByEmail(String email);
	
	//veritabanında Email arıyor
	User findById(int id);
	User findByEmail(String email);
	
	//@Modifying()
	//@Query("delete from User where b.id=:id")
	//void deleteUsers(@Param("id") int id);
}