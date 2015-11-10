package org.csstudio.iter.opibuilder.widgets;

import org.csstudio.iter.opibuilder.widgets.LabeledTextInputModel;
import org.csstudio.opibuilder.widgets.editparts.Draw2DTextInputEditpartDelegate;
import org.csstudio.opibuilder.widgets.editparts.LabelCellEditorLocator;
import org.csstudio.opibuilder.widgets.editparts.TextInputEditpart;
import org.csstudio.swt.widgets.figures.TextInputFigure;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * An override for the default EditPart for the text input widget.
 * Differences with the default TextInputEditPart: it uses an IterTextEditManager instance for DirectEdit,
 * giving the TextInput widget the option of setting a different background color when the control has focus,
 * and saving the value to the PV without pressing ENTER (on loss of focus).
 *  
 * It must be used together with LabeledTextInputModel and IterTextEditManager.
 *
 * @author Boris Versic
 */
public class LabeledTextInputEditpart extends TextInputEditpart {
	
	public LabeledTextInputEditpart() {
		super();
	}

    @Override
    public LabeledTextInputModel getWidgetModel() {
        return (LabeledTextInputModel) getModel();
    }

    @Override
    protected IFigure doCreateFigure() {
        initFields();

        if(shouldBeTextInputFigure()){
            TextInputFigure textInputFigure = (TextInputFigure) createTextFigure();
            initTextFigure(textInputFigure);
            delegate = new Draw2DTextInputEditpartDelegate(
                    this, getWidgetModel(), textInputFigure);

        }else{
            delegate = new NativeLabeledTextEditpartDelegate(this, getWidgetModel());
        }

        getPVWidgetEditpartDelegate().setUpdateSuppressTime(-1);
        updatePropSheet();

        return delegate.doCreateFigure();
    }    
    
	@Override
    protected void performDirectEdit() {
    	final LabeledTextInputModel model = getWidgetModel();
        new IterTextEditManager(this, new LabelCellEditorLocator(
                (Figure) getFigure()), getWidgetModel().isMultilineInput(),
        		new Color(Display.getDefault(), model.getBackgroundFocusColor()), model.isConfirmOnFocusLost()).show();
    }
}