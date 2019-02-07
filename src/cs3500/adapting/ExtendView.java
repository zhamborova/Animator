package cs3500.adapting;

import java.util.List;
import java.util.Map;

import cs3500.provider.ShapeType;
import cs3500.provider.Transformation;
import cs3500.view.IView;

/**
 * Ideally we would have used this interface to add the extra functionality our implementation
 * lacked to support the provider's model interface.
 */
public interface ExtendView extends IView {

  /**
   * Takes in the transformations to display.
   *
   * @param transformations The transformations to display.
   */
  void acceptTransformations(List<Transformation> transformations);

  /**
   * Takes in a mapping of shape IDs to what type they are.
   *
   * @param idsToTypes The mapping.
   */
  void acceptIDsToTypes(Map<String, ShapeType> idsToTypes);


}
