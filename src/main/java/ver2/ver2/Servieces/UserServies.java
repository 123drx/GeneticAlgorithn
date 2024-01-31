package ver2.ver2.Servieces;
import java.util.List;

import ver2.ver2.Repositorys.UserRepository;
import ver2.ver2.objects.User;


public class UserServies {
      private ver2.ver2.Repositorys.UserRepository Trepo;

    public UserServies(UserRepository r)
    {
        this.Trepo = r;
    }
    
    public void insertUser(User t)
    {
        Trepo.insert(t);
    }

    public List<User> GetAllUsers()
    {
        return Trepo.findAll(); 
    }
}
