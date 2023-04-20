package org.example.view;

import org.example.controller.Controller;
import org.example.model.ModelData;

public interface View {
    void refreshCatalogue(ModelData modelData);

    void setController(Controller controller);
}
