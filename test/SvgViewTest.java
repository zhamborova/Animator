import org.junit.Test;

import cs3500.animation.Animation;
import cs3500.animation.RoIAnimation;
import cs3500.view.IView;
import cs3500.view.SvgView;

import static junit.framework.TestCase.assertEquals;

public class SvgViewTest {

  RoIAnimation anim = new Animation.Builder()
          .declareShape("R", "rectangle")
          .declareShape("E", "ellipse")
          .addMotion("R", 1, 100, 300, 2, 3, 3, 3, 3, 10,
                  200, 300, 2, 3, 3, 3, 3)
          .build()
          .toRO();

  IView svg = new SvgView(1, "test.txt", anim);

  @Test
  public void exportAnimation() {
    assertEquals("<svg width=\"100\" height=\"100\" version=\"1.1\" " +
            "viewBox=\"0 0 100 100\" " + "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"0\" y=\"0\" width=\"0\" height=\"0\" fill=\"rgb(0,0,0)\" " +
            "visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" attributeName=\"fill\" " +
            "from=\"rgb(0,0,0)\" to=\"rgb(3,3,3)\" fill=\"freeze\" />\n" +
            "fill<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" " +
            "attributeName=\"width\"" +
            " from=\"0\" to=\"2\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" " +
            "attributeName=\"height\" " +
            "from=\"0\" to=\"3\" fill=\"freeze\" />\n" +
            "width<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\"" +
            " attributeName=\"x\" " +
            "from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" attributeName=\"y\" " +
            "from=\"0\" to=\"300\" fill=\"freeze\" />\n" +
            "x<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9001ms\" attributeName=\"x\" " +
            "from=\"100\" to=\"200\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9001ms\" attributeName=\"y\" " +
            "from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
            "x</rect>\n" +
            "<ellipse id=\"E\" cx=\"0\" cy=\"0\" rx=\"0\" ry=\"0\" fill=\"rgb(0,0,0)\" " +
            "visibility=\"visible\" >\n" +
            "</ellipse>\n" +
            "</svg>\n", svg.exportAnimation());
  }

}