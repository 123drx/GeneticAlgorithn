package ver2.ver2.objects;

public class Subject {
    private String SubjectName;
    private String TeacherName;
    private int WeaklyHours;

    public Subject()
    {

    }

    public Subject(Subject otherSubject) {
        this.SubjectName = otherSubject.getSubjectName();
        this.TeacherName = otherSubject.getTeacherName();
        this.WeaklyHours = otherSubject.getWeaklyHours();
    }

    public Subject(String Name)
    {
        this.SubjectName = Name;
    }
    
    public String getSubjectName() {
        return SubjectName;
    }
    
    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
    
    public String getTeacherName() {
        return TeacherName;
    }
    
    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }
    
    public int getWeaklyHours() {
        return WeaklyHours;
    }
    
    public void setWeaklyHours(int weaklyHours) {
        WeaklyHours = weaklyHours;
    }
    



}
