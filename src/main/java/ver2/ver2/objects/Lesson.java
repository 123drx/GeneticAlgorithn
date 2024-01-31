package ver2.ver2.objects;

public class Lesson {

    private String LessonSubject;
    private String Teacher;
    private int StartHour;
    private int EndHour;

    public Lesson(String LessonSubject,String Teacher,int StartHour,int EndHour)
    {
        this.LessonSubject = LessonSubject;
        this.Teacher = Teacher;
        this.StartHour = StartHour;
        this.EndHour = EndHour;
    }

    public Lesson(Lesson lesson)
    {
        this.LessonSubject = lesson.getLessonSubject();
        this.Teacher = lesson.getTeacher();
        this.StartHour = lesson.getStartHour();
        this.EndHour = lesson.getEndHour();
    }

    public Lesson()
    {

    }

    public String getLessonSubject() {
        return LessonSubject;
    }

    public void setLessonSubject(String lessonSubject) {
        LessonSubject = lessonSubject;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public int getStartHour() {
        return StartHour;
    }

    public void setStartHour(int startHour) {
        StartHour = startHour;
    }

    public int getEndHour() {
        return EndHour;
    }

    public void setEndHour(int endHour) {
        EndHour = endHour;
    }
    
    public String GetLesson()
    {
        String s ;
        s = ""+LessonSubject +" "+ Teacher;
        return s;
    }
    
    public void PrintLesson()
    {
        String s ;
        s = ""+LessonSubject +" "+ Teacher;
        System.out.println(s);
    }
 
    public String PrintHours()
    {
        String s; 
        s = StartHour + " - " + EndHour;
        return s;
    }



}
