/**
* This class extends the RTextScrollPane class
* @author Tristan Furno
* @version 1.0
*/
package view.elements;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextAreaEditorKit.*;

public class AreaToSave extends RSyntaxTextArea{



  /**
  * Attribute path to know the save path
  */
  private String savePath;

  /**
  * Constructor of the class AreaToSave
  * @param a the weight
  * @param b the height
  */
  public AreaToSave(int a, int b){
    super(a, b);
    this.savePath = null;
  }

  /**
  * Constructor of the class AreaToSave
  */
  public AreaToSave(){
    super();
  }

  /**
  * Getter of the attribute savePath
  * @return the Attribute savePath
  */
  public String getSavePath(){
    return this.savePath;
  }

  /**
  * Setter of the attribute savePath
  * @param path the path
  */
  public void setSavePath(String path){
    if(path != null){
      this.savePath = path;
    }
  }

}
