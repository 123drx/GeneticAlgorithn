package ver2.ver2.objects;
import java.util.ArrayList;
import java.util.List;

public class SchoolClass {
    private String className;
    private Schedule Schedule;
    private List<Subject> subjects = new ArrayList<>();
    private List<String> Teachers = new ArrayList<>();
    


    public SchoolClass()
    {

    }

    public void addEmptySubject()
    {
        Subject s = new Subject();
        s.setSubjectName("Empty");
        s.setTeacherName("Non");
        subjects.add(s);
    }

    public SchoolClass(SchoolClass otherClass) {
        this.className = otherClass.className;
        this.Schedule = new Schedule(otherClass.Schedule);

        // Deep copy for subjects
        for (Subject subject : otherClass.subjects) {
            this.subjects.add(new Subject(subject));
        }

        // Copy Teachers (assuming it's just a list of names, so a shallow copy is fine)
        this.Teachers = new ArrayList<>(otherClass.Teachers);
    }

    public void printsubjects()
    {
        for(Subject s : this.subjects)
        {
            System.out.println("stubject " + s.getSubjectName());
        }
    }

    public boolean isSubjectExist(String SubjectName)
    {
        for(Subject s : subjects)
        {
            if(s.getSubjectName().equals(SubjectName))
            {
                return true;
            }
        }
        return false;
    }

    public void initSchedule()
    {
        this.Schedule = new Schedule();
        this.Schedule.InitSchedule();
    }
    public SchoolClass(String Name)
    {
        this.className = Name;
    }

    public String getClassName() {
        return className;
    }
    
    public Schedule getSchedule() {
        return Schedule;
    }
    
    public void setSchedule(Schedule s) {
        this.Schedule = s;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }
    
    public void addteacher(String teacher)
    {
        this.Teachers.add(teacher);
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    public void addSubject(Subject Subject)
    {
        this.subjects.add(Subject);
        if(!IsTeacherExists(Subject.getTeacherName()))
        {
            addteacher(Subject.getTeacherName());
        }
    }
    
    public void RemoveSubject(int index)
    {
        this.subjects.remove(index);
    }
    
    public String tostring()
    {
        String s;
        s= "class" +className+"\n";
        s += "Subjects : ";
        for(Subject sbj : subjects)
        {
            s += sbj.getSubjectName()+" , ";
        }
        return s; 
    }
    
    public boolean IsTeacherExists(String Name)
    {
        if(this.Teachers == null)
        {
            return false;
        }
        for(String t : this.Teachers)
        {
            if( t.equals(Name))
            return true;
        }
        return false;
    }

    public List<String> getTeachers() {
        return Teachers;
    }

    public void setTeachers(List<String> teachers) {
        Teachers = teachers;
    }
    
}
