package databases;

import classes.Record;
import classes.User;

import java.util.ArrayList;
import java.util.List;

public class RecordShelf {
    List<Record> recordList = new ArrayList<>();

    private static RecordShelf instance = new RecordShelf();
    public static RecordShelf getInstance(){
        return instance;
    }

    public boolean contains(User user, String ISBN) {
        for(Record record : recordList){
            if(record.is(user,ISBN)){
                return true;
            }
        }
        return false;
    }

    public void putRecord(User user, String ISBN) {
        Record record = new Record(user.getId(),ISBN);
        recordList.add(record);
    }

    public void remove(User user, String ISBN) {
        int index = -1;
        for(int i = 0;i < recordList.size();i ++){
            Record record = recordList.get(i);
            if(record.is(user,ISBN)){
                index = i;
                recordList.remove(i);
                break;
            }
        }
    }
}
