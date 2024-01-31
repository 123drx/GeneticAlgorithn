package ver2.ver2.Repositorys;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ver2.ver2.objects.Schedule;




@Repository
public interface SchedualRepository extends MongoRepository<Schedule,ObjectId> {
    public Schedule[] findbyclassname(String s);
    
} 