package src;

import src.dao.SubjectDao;
import src.model.Subject;

public class Main {

    public static void main(String[] args) {
        SubjectDao subjectDao = new SubjectDao();
        subjectDao.save(new Subject("MC"));
        System.out.println(subjectDao.getById(1));

    }
}
