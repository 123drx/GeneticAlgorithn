package ver2.ver2.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Teacher {
    public final int MaxDays = Schedule.MaxDays;
    public final int MaxHours = Schedule.MaxHours;
    public final int LunchHour = Schedule.LunchHour;
    public final int StartingHour = Schedule.StartingHour;

    private String Name;
    private boolean[][] HourPrefrences = new boolean[MaxDays][MaxHours];
    private boolean[] DayConstrains = new boolean[MaxDays];
    private List<String> Subjects = new ArrayList<>();

    private static final Map<Integer, String> daysOfWeekMap = new HashMap<>();
    static {
        daysOfWeekMap.put(0, "Sunday");
        daysOfWeekMap.put(1, "Monday");
        daysOfWeekMap.put(2, "Tuesday");
        daysOfWeekMap.put(3, "Wednesday");
        daysOfWeekMap.put(4, "Thursday");
    }

    public Teacher(Teacher otherTeacher) {
        this.Name = otherTeacher.Name;

        // Deep copy for HourPreferences
        for (int i = 0; i < MaxDays; i++) {
            System.arraycopy(otherTeacher.getHourPrefrences()[i], 0, this.getHourPrefrences()[i], 0, MaxHours);
        }

        // Deep copy for DayConstraints
        System.arraycopy(otherTeacher.getDayConstrains(), 0, this.getDayConstrains(), 0, MaxDays);

        // Copy Subjects (assuming it's just a list of names, so a shallow copy is fine)
        this.Subjects = new ArrayList<>(otherTeacher.Subjects);
    }

    public Constrains[] getConstrains() {
        int starthour = 0;
        int EndHour = 0;
        Constrains[] consts = new Constrains[Schedule.MaxDays];
        for (int day = 0; day < Schedule.MaxDays; day++) {
            boolean bol = false;
            if (this.getDayConstrains()[day] == true) {
                for (int hour = 0; hour < Schedule.MaxHours; hour++) {
                    if (this.getHourPrefrences()[day][hour] == true && bol==false) {
                        bol = true;
                        starthour = hour;

                    } else if(this.getHourPrefrences()[day][hour] == false) {
                        if (bol == true) {
                            EndHour = hour;
                            consts[day] = new Constrains(starthour, EndHour);
                            break;
                        }
                    }
                }
            } else {
                consts[day] = null;
            }
        }
        return consts;
    }

    public void printSubjects() {
        String s = "Subjects : ";
        for (String str : this.Subjects) {
            s = s + " , " + str;
        }
        System.out.println(s);
    }

    public Teacher(String name, boolean[][] hourPrefrences, boolean[] dayConstrains, List<String> subjects) {
        Name = name;
        HourPrefrences = hourPrefrences;
        DayConstrains = dayConstrains;
        Subjects = subjects;
    }

    public Teacher() {

    }

    public Teacher(String Name) {
        this.Name = Name;
    }

    public void addsubject(String subjectName) {
        this.Subjects.add(subjectName);
    }

    public List<String> getSubjects() {
        return Subjects;
    }

    public void setSubjects(List<String> subjects) {
        Subjects = subjects;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setConstrains(Constrains[] constrains) {
        for (int i = 0; i < MaxDays; i++) {
            if (constrains[i] == null) {
                this.DayConstrains[i] = false;
                for (int j = 0; j < Schedule.MaxDays; j++) {
                    HourPrefrences[i][j] = false;
                }
            } else {
                this.DayConstrains[i] = true;
                for (int j = constrains[i].getStartHour() - StartingHour; j < constrains[i].getEndHour()
                        - StartingHour; j++) {
                    HourPrefrences[i][j] = true;
                }
            }
        }
    }

    public void setConstrains1(Constrains[] constrains) {
        for (int i = 0; i < MaxDays; i++) {
            if (constrains[i] == null) {
                this.DayConstrains[i] = false;
                for (int j = 0; j < Schedule.MaxDays; j++) {
                    HourPrefrences[i][j] = false;
                }
            } else {
                this.DayConstrains[i] = true;
                for (int j = constrains[i].getStartHour(); j <= constrains[i].getEndHour(); j++) {
                    HourPrefrences[i][j] = true;
                }
            }
        }
    }

    public int getdistancefromhour(int day) {
        int retint = 0;
        for (int hour = 0; hour < this.MaxHours; hour++) {
            if (this.getHourPrefrences()[day][hour]) {

            }
        }
        return retint;
    }

    public void setConstrains(boolean[][] constrains) {
        this.HourPrefrences = constrains;

    }

    public void SetDayConstrains(boolean[] arr) {
        this.DayConstrains = arr;
    }

    public boolean[][] getHourPrefrences() {
        return HourPrefrences;
    }

    public boolean[] getDayConstrains() {
        return DayConstrains;
    }

    public void printconstrains() {
        System.out.println("Days he can work");
        for (int i = 0; i < MaxDays; i++) {
            if (this.DayConstrains[i] == true) {
                System.out.println("can Work on " + daysOfWeekMap.get(i) + " ");
                System.out.print("in ");
                for (int j = 0; j < MaxHours; j++) {
                    if (this.HourPrefrences[i][j] == true) {
                        System.out.print(" " + (j + StartingHour) + ",");
                    }
                }
                System.out.println();
            } else {
                System.out.println("Can't Work On " + daysOfWeekMap.get(i));
            }
        }
    }

    public void printDayConstrains() {
        for (int i = 0; i < this.DayConstrains.length; i++) {
            if (this.DayConstrains[i] == true) {
                System.out.println("Day " + (i + 1) + " = " + "True");
            } else {
                System.out.println("Day " + (i + 1) + " = " + "False");
            }

        }
    }

    public List<Lesson> GetLessonFromTeachers(List<Teacher> teachers) {
        List<Lesson> Subjects = new ArrayList<>();
        Set<Lesson> TempSubject = new HashSet<>();
        for (Teacher t : teachers) {
            for (String subject : t.getSubjects()) {
                Lesson lesson = new Lesson(subject, t.getName(), -1, -1);
                TempSubject.add(lesson);
            }
        }
        for (Lesson s : TempSubject) {
            // s.PrintLesson();
            Subjects.add(s);
        }

        return Subjects;
    }

}
