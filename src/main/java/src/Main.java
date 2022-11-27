package src;

import src.dao.SubjectDao;

public class Main {

    public static void main(String[] args) {
        SubjectDao subjectDao = new SubjectDao();
        subjectDao.save();
        System.out.println(subjectDao.get(1));

    }
}
