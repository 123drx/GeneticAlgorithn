package ver2.ver2.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ver2.ver2.Servieces.ScheduleServies;

public class Schedule {

    private Lesson[][] Schedual;
    private String classname;

    // TeacherServies TeacherServies;
    public static final int MaxDays = 5;
    public static final int MaxHours = 9;
    public static final int LunchHour = 11;
    public static final int StartingHour = 8;
    private static final Map<Integer, String> daysOfWeekMap = new HashMap<>();
    ScheduleServies Sservies;
    static {
        daysOfWeekMap.put(0, "Sunday");
        daysOfWeekMap.put(1, "Monday");
        daysOfWeekMap.put(2, "Tuesday");
        daysOfWeekMap.put(3, "Wednesday");
        daysOfWeekMap.put(4, "Thursday");
    }

    /*
     * public boolean IsTeacherScheduled(Teacher t , int day , int hour)
     * {
     * for(String g:t.getClasses())
     * {
     * Schedule[] s =(Sservies.findbyclassname(g));
     * for(Schedule sc : s)
     * {
     * if(sc.getSchedual()[day][hour].getTeacher()==t.getName())
     * {
     * return true;
     * }
     * }
     * 
     * 
     * }
     * 
     * return false;
     * }
     */
    // public boolean isTeacherScheduledOnDay(Teacher teacher, int day)
    // {
    // for(String g:teacher.getClasses())
    // {
    // Schedule[] schedule =(Sservies.findbyclassname(g));
    // for(Schedule sc : schedule)
    // {
    // List<String> s = sc.getTeachersInDay(day);
    // for(String v : s)
    // {
    // if(v==teacher.getName())
    // {
    // return true;
    // }
    // }
    // }
    // }
    // return false;
    // }
    // public int TeacherScheduledtimes(Teacher teacher , int day , int hour)
    // {
    // int value = 0 ;
    // for(String g:teacher.getClasses())
    // {
    // Schedule[] schedule =(Sservies.findbyclassname(g));
    // for(Schedule sc : schedule)
    // {
    // if(sc.getSchedual()[day][hour].getTeacher()==teacher.getName())
    // {
    // value +=1;
    // }
    // }
    // }
    // return value;
    // }

    public Schedule() {
        this.Schedual = new Lesson[MaxDays][MaxHours];
    }

    public Schedule(Lesson[][] schedule, String s) {
        this.Schedual = schedule;
        this.classname = s;

    }

    public Schedule(String s) {
        this.classname = s;

    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<String> getTeachersInDay(int day) {
        Set<String> Tempteacher = new HashSet<>();
        List<String> teachers = new ArrayList<>();

        for (int i = 0; i < MaxHours; i++) {
            String teacher = this.Schedual[day][i].getTeacher();
            Tempteacher.add(teacher);
        }
        for (String s : Tempteacher) {
            teachers.add(s);
        }
        return teachers;
    }

    // public List<Teacher> getTeachers() {
    // Set<String> Tempteacher = new HashSet<>();
    // List<Teacher> teachers = new ArrayList<>();
    // for (int i = 0; i < MaxDays; i++) {
    // for (int j = 0; j < MaxHours; j++) {
    // if(this.Schedual[i][j] == null)
    // {
    // continue;
    // }
    // String teacher = this.Schedual[i][j].getTeacher();
    // Tempteacher.add(teacher);
    // }
    // }
    // for (String s : Tempteacher) {
    // Teacher t = TeacherServies.findByName(s);
    // teachers.add(t);
    // }
    // return teachers;
    // }

    public Lesson[][] getSchedule() {
        return Schedual;
    }

    public void setSchedual(Lesson[][] schedual) {
        Schedual = schedual;
    }

    public void FillThisScheduleWithLessons(List<Subject> subjectlist) {
        Random r = new Random();
        int rand;
        for (int i = 0; i < MaxDays; i++) {
            for (int j = 0; j < MaxHours; j++) {
                if (j != LunchHour - StartingHour) {
                    rand = r.nextInt(0, subjectlist.size());
                    Lesson s = new Lesson();
                    Subject randomsubject = subjectlist.get(rand);
                    s.setLessonSubject(randomsubject.getSubjectName());
                    s.setTeacher(randomsubject.getTeacherName());

                    s.setStartHour(j + StartingHour);
                    s.setDay(i);
                    this.SetLesson(i, j, s);
                }
            }
        }
    }

    public Schedule FillScheduleWithLessons(List<Subject> subjectlist) {
        Random r = new Random();
        Schedule schd = new Schedule();
        schd.InitSchedule();
        int rand;
        for (int i = 0; i < MaxDays; i++) {
            for (int j = 0; j < MaxHours; j++) {
                if (j != LunchHour - StartingHour) {
                    rand = r.nextInt(0, subjectlist.size());
                    Lesson s = new Lesson();
                    Subject randomsubject = subjectlist.get(rand);
                    s.setLessonSubject(randomsubject.getSubjectName());
                    s.setTeacher(randomsubject.getTeacherName());

                    s.setStartHour(j + StartingHour);
                    s.setDay(i);
                    schd.SetLesson(i, j, s);
                }
            }
        }
        return schd;
    }

    public void printSchedule() {
        System.out.println("Schedule for class " + this.classname + ":");

        // Print column headers (hours)
        System.out.print("\t");
        for (int hour = 0; hour < MaxHours; hour++) {
            System.out.printf("%2d" + ":00\t\t", (StartingHour + hour));
        }
        System.out.println();

        // Print schedule content
        for (int day = 0; day < MaxDays; day++) {
            System.out.print("Day " + (day + 1) + ":\t");

            for (int hour = 0; hour < MaxHours; hour++) {
                Lesson currentLesson = Schedual[day][hour];
                if (currentLesson.getLessonSubject() != null) {
                    // Print lesson information
                    if (currentLesson.getTeacher() != null) {
                        System.out.print(currentLesson.getLessonSubject() + "," + currentLesson.getTeacher() + "\t");
                    } else {
                        System.out.print(currentLesson.getLessonSubject() + "\t" + "\t");
                    }
                } else {
                    // Print empty slot
                    System.out.print("--------\t");
                }
            }
            System.out.println();
        }
    }

    public String getScheduleAsString() {
        if (this.Schedual == null)
            return null;
        StringBuilder scheduleString = new StringBuilder();
        scheduleString.append("Schedule for class ").append(this.classname).append(":\n");

        // Append column headers (hours)
        scheduleString.append("\t");
        for (int hour = 0; hour < Schedule.MaxHours; hour++) {
            scheduleString.append(String.format("%2d" + ":00\t\t", (Schedule.StartingHour + hour)));
        }
        scheduleString.append("\n");

        // Append schedule content
        for (int day = 0; day < Schedule.MaxDays; day++) {
            scheduleString.append("Day ").append(day + 1).append(":\t");

            for (int hour = 0; hour < Schedule.MaxHours; hour++) {
                Lesson currentLesson = this.getSchedule()[day][hour];
                if (currentLesson.getLessonSubject() != null) {
                    // Append lesson information
                    if (currentLesson.getTeacher() != null) {
                        scheduleString.append(currentLesson.getLessonSubject()).append(",")
                                .append(currentLesson.getTeacher()).append("\t");
                    } else {
                        scheduleString.append(currentLesson.getLessonSubject()).append("\t\t");
                    }
                } else {
                    // Append empty slot
                    scheduleString.append("--------\t");
                }
            }
            scheduleString.append("\n");
        }

        return scheduleString.toString();
    }

    public void printScheduleteachers() {
        System.out.println("Schedule for class " + this.classname + ":");

        // Print column headers (hours)
        System.out.print("\t");
        for (int hour = 0; hour < MaxHours; hour++) {
            System.out.printf("%2d" + ":00\t\t", (StartingHour + hour));
        }
        System.out.println();

        // Print schedule content
        for (int day = 0; day < MaxDays; day++) {
            System.out.print("Day " + (day + 1) + ":\t");

            for (int hour = 0; hour < MaxHours; hour++) {
                Lesson currentLesson = Schedual[day][hour];
                if (currentLesson.getLessonSubject() != null) {
                    // Print lesson information
                    if (currentLesson.getTeacher() != null) {
                        System.out.print(currentLesson.getTeacher() + "\t" + "\t");
                    } else {
                        System.out.print(currentLesson.getLessonSubject() + "\t" + "\t");
                    }
                } else {
                    // Print empty slot
                    System.out.print("--------\t");
                }
            }
            System.out.println();
        }
    }

    public void crossover(Schedule parent1, Schedule parent2) {
        Random random = new Random();
        Schedule child = new Schedule();
        child.InitSchedule();
        // 2 random number 1 for a random hour to start copying utill that number hit in
        // the if
        // secend number for a rundom day to start copying utill that number hit in the
        // if
        int crossoverDayPoint = random.nextInt(1, MaxDays - 1);
        int crossoverHourPoint = random.nextInt(2, MaxHours - 2);
        // third random for how we are copying from the random day or the random hour
        int randomint = random.nextInt(2);
        if (randomint == 1) {
            for (int day = 0; day < MaxDays; day++) {
                for (int hour = 0; hour < MaxHours; hour++) {
                    if (day < crossoverDayPoint) {

                        child.getSchedule()[day][hour] = parent1.getSchedule()[day][hour];
                    } else {
                        // Copy genes from parent2 to child after the crossover point
                        child.getSchedule()[day][hour] = parent2.getSchedule()[day][hour];
                    }
                }
            }
            this.setSchedual(child.getSchedule());
        } else {
            for (int day = 0; day < MaxDays; day++) {
                for (int hour = 0; hour < MaxHours; hour++) {
                    if (hour < crossoverHourPoint) {

                        child.getSchedule()[day][hour] = parent1.getSchedule()[day][hour];
                    } else {
                        // Copy genes from parent2 to child after the crossover point
                        child.getSchedule()[day][hour] = parent2.getSchedule()[day][hour];
                    }
                }
            }
        }
        this.setSchedual(child.getSchedule());
    }

    public Schedule(Schedule otherSchedule) {
        this.Schedual = new Lesson[MaxDays][MaxHours];
        for (int i = 0; i < MaxDays; i++) {
            for (int j = 0; j < MaxHours; j++) {
                this.Schedual[i][j] = new Lesson(otherSchedule.Schedual[i][j]);
            }
        }
        this.classname = otherSchedule.classname;
    }

    // Function to perform mutation on a schedule
    public Schedule mutate(List<Subject> subjects) {
        Schedule mutated = new Schedule(this);
        Random random = new Random();
        //Get a Rundom Subject
       Subject s  = subjects.get(random.nextInt(subjects.size()));
       //Create the lesson the Mutated is gonna get
       Lesson NewLesson = new Lesson();
       NewLesson.setLessonSubject(s.getSubjectName());
       NewLesson.setTeacher(s.getTeacherName());
       
    

       int randHour = random.nextInt(MaxHours);
       int randDay = random.nextInt(MaxDays);
       
       while(randHour == LunchHour - StartingHour)
       {
        randHour = random.nextInt(MaxHours);
       }
       while(mutated.Schedual[randDay][randHour].getTeacher().equals(NewLesson.getTeacher())&&mutated.Schedual[randDay][randHour].getLessonSubject().equals(NewLesson.getLessonSubject()))
       {
        s  = subjects.get(random.nextInt(subjects.size()));
        NewLesson.setLessonSubject(s.getSubjectName());
        NewLesson.setTeacher(s.getTeacherName());
       }
       Lesson ss = this.getSchedule()[randDay][randHour];
       NewLesson.setStartHour(ss.getStartHour());
       NewLesson.setDay(ss.getDay());
       mutated.Schedual[randDay][randHour] = NewLesson;
       //System.out.println("Replaced (day :"+(randDay+1)+",Hour "+(randHour+StartingHour)+")");
       return mutated;
    }

     public Schedule Bigmutate(List<Subject> subjects) {
        Schedule mutated = new Schedule(this);
        Random random = new Random();
        //Get a Rundom Subject
        for(int i = 0 ; i < 2 ;i++)
       {Subject s  = subjects.get(random.nextInt(subjects.size()));
       //Create the lesson the Mutated is gonna get
       Lesson NewLesson = new Lesson();
       NewLesson.setLessonSubject(s.getSubjectName());
       NewLesson.setTeacher(s.getTeacherName());
       
    

       int randHour = random.nextInt(MaxHours);
       int randDay = random.nextInt(MaxDays);
       
       while(randHour == LunchHour - StartingHour)
       {
        randHour = random.nextInt(MaxHours);
       }
       while(mutated.Schedual[randDay][randHour].getTeacher().equals(NewLesson.getTeacher())&&mutated.Schedual[randDay][randHour].getLessonSubject().equals(NewLesson.getLessonSubject()))
       {
        s  = subjects.get(random.nextInt(subjects.size()));
        NewLesson.setLessonSubject(s.getSubjectName());
        NewLesson.setTeacher(s.getTeacherName());
       }
       Lesson ss = this.getSchedule()[randDay][randHour];
       NewLesson.setStartHour(ss.getStartHour());
       NewLesson.setDay(ss.getDay());
       mutated.Schedual[randDay][randHour] = NewLesson;
       //System.out.println("Replaced (day :"+(randDay+1)+",Hour "+(randHour+StartingHour)+")");
    }
       return mutated;
    }

    public Schedule spesificmutate(int day, int hour) {
        Schedule mutated = new Schedule(this);
        Random random = new Random();
        // another 2 randoms
        int day2 = random.nextInt(MaxDays);
        int hour2 = random.nextInt(MaxHours);
        if (hour2 == LunchHour - StartingHour) {
            hour2++;
        }
        while (mutated.getSchedule()[day][hour].getTeacher().equals(mutated.getSchedule()[day2][hour2].getTeacher())) {
            day2 = random.nextInt(MaxDays);
            hour2 = random.nextInt(MaxHours);
            if (hour == LunchHour - StartingHour) {
                hour = (hour + 1) % MaxHours;
            }
        }

        // System.out.println("replaced the (" + hour + "," + day + ")" + "with(" +
        // hour2 + "," + day2 + ")");
        Lesson s = new Lesson();
        s = mutated.Schedual[day][hour];
        mutated.Schedual[day][hour] = mutated.Schedual[day2][hour2];
        mutated.Schedual[day2][hour2] = s;
        this.setSchedual(mutated);
        return mutated;
    }

    public void setSchedual(Schedule s) {
        this.Schedual = s.getSchedule();
        this.classname = s.getClassname();
    }

    public void SetLesson(int i, int j, Lesson l) {
        this.Schedual[i][j] = l;
    }

    // public void printSchedule1() {
    // // Print table header
    // System.out.println(
    // "----------------------------------------------------------scheduale---------------------------------------------------------");
    // System.out.printf("%-20s", " ");
    // for (int day = 0; day <= MaxDays; day++) {
    // System.out.printf("| %-15s", daysOfWeekMap.get(day));
    // }
    // System.out.println("|");
    // // Print table rows
    // for (int hour = 0; hour <= MaxDays; hour++) {
    // System.out.printf("%-20s", "Hour " + hour);
    // for (int day = 0; day <= MaxDays; day++) {
    // Lesson lesson = Schedual[day][hour];
    // if (lesson != null) {
    // System.out.printf("|"+"| %-15s", lesson.GetLesson() +"|"+ "\n" +
    // lesson.PrintHours());
    // } else {
    // System.out.printf("| %-15s", "|No Lesson|");
    // }
    // }
    // System.out.println("|");
    // }
    // System.out.println(
    // "----------------------------------------------------------scheduale---------------------------------------------------------");
    // }

    public void fillschedualwithLesson(Lesson l) {

        for (int i = 0; i < MaxDays; i++) {
            for (int j = 0; j < MaxHours; j++) {
                if (j != LunchHour - StartingHour) {
                    SetLesson(i, j, l);
                }
            }
        }
    }

    public int CountWeeklyHours(String subject) {
        int count = 0;
        for (Lesson[] ss : this.getSchedule()) {
            for (Lesson s : ss) {
                if (s.getLessonSubject().equals(subject)) {
                    count++;
                }
            }

        }
        return count;
    }

    public void InitSchedule() {
        this.Schedual = new Lesson[MaxDays][MaxHours];
        for (int i = 0; i < MaxDays; i++) {
            for (int j = 0; j < MaxHours; j++) {
                if (j == LunchHour - StartingHour) {
                    this.Schedual[i][j] = new Lesson("BreakFast", null, i + StartingHour, j + StartingHour + 1);
                } else {
                    this.Schedual[i][j] = new Lesson();
                }
            }
        }
    }

}
