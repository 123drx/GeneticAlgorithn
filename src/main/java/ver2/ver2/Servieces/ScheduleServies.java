package ver2.ver2.Servieces;

import java.util.List;

import org.springframework.stereotype.Service;

import ver2.ver2.Repositorys.SchedualRepository;
import ver2.ver2.objects.Schedule;


@Service
public class ScheduleServies {
    private SchedualRepository Srepo;
    
    public ScheduleServies(SchedualRepository Screpo)
    {
        this.Srepo = Screpo;
    }

    public void InsertSchedual(Schedule s )
    {
        Srepo.insert(s);
    }

    public Schedule[] findbyclassname(String s)
    {
        return Srepo.findbyclassname(s);
    }

    
   public List<Schedule> getAllScheduals()
   {
      return Srepo.findAll();
   }

    


}
