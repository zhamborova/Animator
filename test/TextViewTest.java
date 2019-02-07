import org.junit.Test;

import cs3500.animation.Animation;
import cs3500.animation.RoIAnimation;
import cs3500.view.IView;
import cs3500.view.TextView;

import static junit.framework.TestCase.assertEquals;

public class TextViewTest {

  RoIAnimation anim = new Animation.Builder()
          .declareShape("R", "rectangle")
          .declareShape("E", "ellipse")
          .addMotion("R", 1, 100, 300, 2, 3, 3, 3, 3,
                  10, 200, 300, 2, 3, 3, 3, 3)
          .addMotion("R", 10, 200, 300, 2, 3, 3, 3, 3,
                  20, 150, 400, 9, 3, 3, 2, 2)
          .addMotion("E", 1, 100, 300, 2, 3, 3, 3, 3,
                  10, 200, 300, 2, 3, 3, 3, 3)
          .addMotion("E", 10, 200, 300, 2, 3, 3, 3, 3,
                  20, 150, 400, 9, 3, 3, 2, 2)
          .build()
          .toRO();

  IView text = new TextView("test.txt", anim);

  // null and empty string files are valid
  // represent an out as system.out
  IView nullFile = new TextView(null, anim);
  IView emptyFileName = new TextView("", anim);


  // a null model should fail
  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    new TextView("test.txt", null);
  }


  @Test
  public void exportAnimation() {
    assertEquals(true, text.exportAnimation().equals(
            "shape R rect\n" +
                    "motion R 1  100 300 2 3 3 3 3   10 200 300 2 3 3 3 3\n" +
                    "motion R 10  200 300 2 3 3 3 3   20 150 400 9 3 3 2 2\n" +
                    "shape E ellipse\n" +
                    "motion E 1  100 300 2 3 3 3 3   10 200 300 2 3 3 3 3\n" +
                    "motion E 10  200 300 2 3 3 3 3   20 150 400 9 3 3 2 2\n"));
  }

}