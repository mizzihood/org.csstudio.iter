package org.csstudio.iter.alarm.beast.ui.alarmtable.actions;

import org.csstudio.iter.alarm.beast.ui.alarmtable.AlarmTableView;
import org.csstudio.iter.alarm.beast.ui.alarmtable.ColumnConfigurer;
import org.csstudio.iter.alarm.beast.ui.alarmtable.ColumnWrapper;
import org.csstudio.iter.alarm.beast.ui.alarmtable.Messages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;

/** Action to allow adding/removing/ordering of table columns
 * 
 *  @author <a href="mailto:jaka.bobnar@cosylab.com">Jaka Bobnar</a>
 */
public class ColumnConfigureAction extends Action {
    private final AlarmTableView view;
    
    /**
     * Constructs a new action that acts on the given alarm table view.
     * 
     * @param view the view to configure its columns
     */
    public ColumnConfigureAction(final AlarmTableView view) {
        super(Messages.ConfigureColumns);
        this.view = view;
    }
    
    @Override
    public void run() {
        ColumnWrapper[] columns = view.getUpdatedColumns();
        ColumnConfigurer configurer = new ColumnConfigurer(view.getViewSite().getShell(), columns);
        if (configurer.open() == IDialogConstants.OK_ID) {
            columns = configurer.getColumns();
            view.setColumns(columns);
        }
    }
}