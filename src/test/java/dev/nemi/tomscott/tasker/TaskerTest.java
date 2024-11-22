package dev.nemi.tomscott.tasker;

import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Scanner;

public class TaskerTest {

  private TaskerDAO dao;

  @BeforeEach
  public void init() {
    dao = new TaskerDAO();
  }

  @Test
  public void insertTest() throws SQLException {
//    @Cleanup Scanner scanner = new Scanner(System.in);
//    System.out.println("Enter Task title: ");
//    String title = scanner.nextLine();
//
//    System.out.println("Enter Task content: ");
//    String content = scanner.nextLine();

    dao.add(TaskerVO.builder().title("Master idea").content("사실 익숙해지기만 하면 돼요").build());
  }

  @Test
  public void laterTest() throws SQLException {
    TaskerDTO task = dao.list().get(0);
    System.out.println(task.toString());
    int id = task.getId();
    dao.later(id);

    TaskerDTO again = dao.get(id);
    System.out.println(again.toString());
    Assertions.assertTrue(task.getEnd().isBefore(again.getEnd()));
  }

  @Test
  public void finishTest() throws SQLException {
    TaskerDTO task = dao.list().get(0);
    System.out.println(task.toString());
    int id = task.getId();

    dao.setFinished(TaskerVO.builder().id(id).finished(1).build());
    TaskerDTO again = dao.get(id);
    System.out.println(again.toString());
    Assertions.assertEquals(1, again.getFinished());
  }
}
