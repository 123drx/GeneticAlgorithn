package ver2.ver2.Servieces;import java.util.List;

import ver2.ver2.Repositorys.TeacherRepository;
import ver2.ver2.objects.Teacher;

public class TeacherServies {

    private TeacherRepository Trepo;

    public TeacherServies(TeacherRepository r)
    {
        this.Trepo = r;
    }
    
    public void insertTeacher(Teacher t)
    {
        Trepo.insert(t);
    }

    public List<Teacher> GetAllTeachers()
    {
        return Trepo.findAll(); 
    }

    public Teacher findByName(String Name)
    {
        return Trepo.findByName(Name);
    }
}
